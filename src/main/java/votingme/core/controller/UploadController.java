package votingme.core.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
public class UploadController {

    private static final String UPLOADED_FOLDER = "/path/to/your/uploads/";

    @PostMapping("/upload")
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile uploadfile) {

        if (uploadfile.isEmpty()) {
            return new ResponseEntity<>("please select a file!", HttpStatus.OK);
        }

        try {
            saveUploadedFilesIn(uploadfile);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>("Successfully uploaded - " +
                uploadfile.getOriginalFilename(), new HttpHeaders(), HttpStatus.OK);

    }

    private void saveUploadedFilesIn(MultipartFile file) throws IOException {

        byte[] bytes = file.getBytes();
        Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
        Files.write(path, bytes);

    }

    private void saveUploadedFilesExt(String linkToFiles) throws IOException {

        //TODO: IMPLEMENT FILES FROM LINK AND SAVING THE LINK AND SOME ADDITIONAL INFO TO THE DATABASE



    }

}
