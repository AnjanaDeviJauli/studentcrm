package org.perscholas.studentcrm.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.perscholas.studentcrm.controller.ImageController;
import org.perscholas.studentcrm.data.ImageRepoI;
import org.perscholas.studentcrm.data.MyUserRepoI;
import org.perscholas.studentcrm.model.Image;
import org.perscholas.studentcrm.model.MyUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;

@Service
@Slf4j
public class ImageService {

    private final Path root = Paths.get("./uploads");


    MyUserRepoI myUserRepoI;
    MyUserService myUserService;
    ImageRepoI imageRepoI;


    @Autowired
    public ImageService(MyUserRepoI myUserRepoI, MyUserService myUserService, ImageRepoI imageRepoI) {
        this.myUserRepoI = myUserRepoI;
        this.myUserService = myUserService;
        this.imageRepoI = imageRepoI;
    }


    public void init() throws Exception {
        try {
            if(Files.exists(root)){
                log.debug("Folder Exists!");
            }else {
                Files.createDirectories(root);
                log.debug("Folder Created!");
            }
        } catch (IOException e) {
            throw new Exception("Could not initialize folder for upload!");
        }
    }


    public void save(MultipartFile file, String email) throws Exception {
        try {

            String ext = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
            log.debug(ext);
            String imageName = email.split("@")[0].concat("-").concat(String.valueOf(LocalDate.now().getYear())).concat(ext);
            log.debug(imageName);
            Files.copy(file.getInputStream(), this.root.resolve(imageName));
            Path p = root.resolve(imageName);
            String url = MvcUriComponentsBuilder
                    .fromMethodName(ImageController.class, "getImage", p.getFileName().toString()).build().toString();
            log.debug(url);

            imageRepoI.save(new Image(imageName,url, myUserRepoI.findByEmailAllIgnoreCase(email).get()));
        } catch (Exception e) {
            if (e instanceof FileAlreadyExistsException) {
                throw new Exception("A file of that name already exists.");
            } else {
                throw new Exception("Error copying file to HD" + file.getOriginalFilename());
            }

        }
    }



    public Resource load(String filename) {
        try {
            Path file = root.resolve(filename);
            Resource resource = new UrlResource(file.toUri());

            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("Could not read the file!");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }


}
