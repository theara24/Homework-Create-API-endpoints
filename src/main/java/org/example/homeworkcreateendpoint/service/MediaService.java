package org.example.homeworkcreateendpoint.service;

import org.example.homeworkcreateendpoint.dto.MediaResponse;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface MediaService {

    MediaResponse upload(MultipartFile file);
    List<MediaResponse> uploadMultiple(MultipartFile[] files);
    ResponseEntity<Resource> downloadByName(String fileNameWithExt);
    void deleteByName(String fileNameWithExt);

}