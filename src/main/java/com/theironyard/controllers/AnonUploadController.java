package com.theironyard.controllers;

import com.theironyard.entities.AnonFile;
import com.theironyard.services.AnonFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * Created by holdenhughes on 11/18/15.
 */
@RestController
public class AnonUploadController {
    @Autowired
    AnonFileRepository files;

    @RequestMapping("/files")
    public List<AnonFile> getFiles(){
        return (List<AnonFile>) files.findAll();
    }

    @RequestMapping("/upload")
    public void upload(HttpServletResponse response, MultipartFile file, boolean isPerm, String description)
            throws Exception {
        File f = File.createTempFile("file",file.getOriginalFilename(), new File("public"));
        FileOutputStream fos = new FileOutputStream(f);
        fos.write(file.getBytes());

       if (files.findAllisNotPerm().size()==3){
            List<AnonFile> setList = files.findAllisNotPerm();
            AnonFile fileRemove = setList.get(0);
            File tempFile = new File("public", fileRemove.name);
            files.delete(fileRemove);
            tempFile.delete();
        }

            AnonFile anonFile = new AnonFile();
            anonFile.originalName = file.getOriginalFilename();
            anonFile.name = f.getName();
            anonFile.isPerm = isPerm;
            anonFile.description = description;


        files.save(anonFile);


        response.sendRedirect("/");
    }

}
