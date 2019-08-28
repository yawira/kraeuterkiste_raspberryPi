package aw.kraeuterkiste_raspberry.photo.controller;

import aw.kraeuterkiste_raspberry.photo.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.image.BufferedImage;


@RestController
public class PhotoController {

    private final PhotoService photoService;

    @Autowired
    public PhotoController(PhotoService photoService) {
        this.photoService = photoService;
    }

    @GetMapping("/upload")
    public String uploadPicture() {
        BufferedImage photo = photoService.takePhoto();
        return photoService.encodePhoto(photo);
    }
}