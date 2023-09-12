package az.coders.CourseAPI.service;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class FileService {


    public ResponseEntity<String> uploadFile(MultipartFile multipartFile, String name) throws IOException {
        String targetDirectory = "C:\\Users\\nikoz\\IdeaProjects\\CourseAPI\\src\\main\\resources\\files";

UUID uuid = UUID.randomUUID();
        String fileName = uuid.toString()  + System.currentTimeMillis() + "-"+
                multipartFile.getOriginalFilename();
        File file = new File(targetDirectory,fileName);
multipartFile.transferTo(file);
return new ResponseEntity<>("Added successfully", HttpStatusCode.valueOf(200));
    }
}
