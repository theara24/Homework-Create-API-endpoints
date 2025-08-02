package org.example.homeworkcreateendpoint.controller;

import org.example.homeworkcreateendpoint.dto.MediaResponse;
import org.example.homeworkcreateendpoint.service.MediaService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1/medias")
@RequiredArgsConstructor
public class MediaController {

    private final MediaService mediaService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MediaResponse upload(@RequestPart MultipartFile file) {
        return mediaService.upload(file);
    }

    @PostMapping("/upload-multiple")
    @ResponseStatus(HttpStatus.CREATED)
    public List<MediaResponse> uploadMultiple(@RequestPart MultipartFile[] files) {
        return mediaService.uploadMultiple(files);
    }

    @GetMapping("/download/{filename}")
    public ResponseEntity<Resource> downloadFile(@PathVariable("filename") String filename) {
        return mediaService.downloadByName(filename);
    }

    @DeleteMapping("/delete/{filename}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String filename) {
        mediaService.deleteByName(filename);
    }

}