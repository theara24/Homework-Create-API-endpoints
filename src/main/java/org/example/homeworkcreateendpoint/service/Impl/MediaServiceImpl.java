package org.example.homeworkcreateendpoint.service.Impl;

import org.example.homeworkcreateendpoint.domain.Media;
import org.example.homeworkcreateendpoint.dto.MediaResponse;
import org.example.homeworkcreateendpoint.repository.MediaRepository;
import org.example.homeworkcreateendpoint.service.MediaService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MediaServiceImpl implements MediaService {

    @Value("${media.server-path}")
    private String serverPath;

    @Value("${media.base-uri}")
    private String baseUri;

    private final MediaRepository mediaRepository;

    @Override
    public MediaResponse upload(MultipartFile file) {

        String name = UUID.randomUUID().toString();

        int lastIndex = Objects.requireNonNull(file.getOriginalFilename()).lastIndexOf(".");
        String extension = file.getOriginalFilename().substring(lastIndex + 1);

        Path path = Paths.get(serverPath + String.format("%s.%s", name, extension));

        try {
            Files.copy(file.getInputStream(),path);
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,e.getMessage());
        }

        Media media = new Media();
        media.setName(name);
        media.setExtension(extension);
        media.setMimeTypeFile(file.getContentType());
        media.setIsDeleted(false);

        media = mediaRepository.save(media);

        return MediaResponse.builder()
                .name(media.getName())
                .mimeTypeFile(media.getMimeTypeFile())
                .size(file.getSize())
                .uri(baseUri+ String.format("%s.%s", name, extension))
                .build();
    }

    @Override
    public List<MediaResponse> uploadMultiple(MultipartFile[] files) {
        List<MediaResponse> media = new ArrayList<>();
        for (MultipartFile file : files) {
            media.add(upload(file));
        }
        return media;
    }

    @Override
    public ResponseEntity<Resource> downloadByName(String fileNameWithExt) {
        Path path = Paths.get(serverPath + fileNameWithExt);
        int lastIndex = fileNameWithExt.lastIndexOf(".");
        String name = fileNameWithExt.substring(0, lastIndex);
        String extension = fileNameWithExt.substring(lastIndex + 1);

        Media mediaFile = mediaRepository.findByNameAndExtension(name, extension)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "File metadata not found."));

        if (mediaFile.getIsDeleted()) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "File has been deleted.");
        }

        if (!Files.exists(path)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "File not found.");
        }

        Resource resource;
        try {
            resource = new UrlResource(path.toUri());
        } catch (MalformedURLException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Malformed path.");
        }

        String mimeType;
        try {
            mimeType = Files.probeContentType(path);
        } catch (IOException e) {
            mimeType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(mimeType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileNameWithExt + "\"")
                .body(resource);
    }

    @Override
    public void deleteByName(String fileNameWithExt) {
        try {
            Files.deleteIfExists(Paths.get(serverPath + fileNameWithExt));

            int lastIndex = fileNameWithExt.lastIndexOf(".");
            String name = fileNameWithExt.substring(0, lastIndex);
            String extension = fileNameWithExt.substring(lastIndex + 1);

            mediaRepository.findByNameAndExtension(name, extension)
                    .ifPresent(media -> {
                        media.setIsDeleted(true);
                        mediaRepository.save(media);
                    });

        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to delete file.");
        }
    }





}