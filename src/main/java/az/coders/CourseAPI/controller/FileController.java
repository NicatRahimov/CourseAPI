package az.coders.CourseAPI.controller;

import az.coders.CourseAPI.service.FileService;
import jakarta.servlet.annotation.MultipartConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("file")

public class FileController {

    @Autowired
    FileService fileService;
@PostMapping("upload")
    public ResponseEntity<String>uploadPhoto(@RequestParam MultipartFile multipartFile,
                                             @RequestParam String name) throws IOException {
        return fileService.uploadFile(multipartFile,name);
    }
}
