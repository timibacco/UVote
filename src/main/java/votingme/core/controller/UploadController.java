package votingme.core.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import votingme.core.service.StorageService;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
public class UploadController {
    @Autowired
    private final StorageService storageService;


    @PostMapping("/upload")
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile uploadfile,
                                            RedirectAttributes redirectAttributes) {

        try {
            storageService.store(uploadfile);


            return ResponseEntity.ok().build();
        } catch (Exception ex) {
            return ResponseEntity.badRequest().build();
        }
    }





    @GetMapping("/upload")
    public String listUploadedFiles(Model model) throws IOException {

        model.addAttribute("files");

        return "upload-data";
    }

    //TODO: IMPLEMENT FILES FROM LINK AND SAVING THE LINK AND SOME ADDITIONAL INFO TO THE DATABASE



    }


