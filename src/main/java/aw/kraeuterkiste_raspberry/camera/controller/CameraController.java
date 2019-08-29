package aw.kraeuterkiste_raspberry.camera.controller;

import aw.kraeuterkiste_raspberry.camera.service.CameraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.image.BufferedImage;


@RestController
@RequestMapping("/camera")
public class CameraController {

    private final CameraService cameraService;

    @Autowired
    public CameraController(CameraService cameraService) {
        this.cameraService = cameraService;
    }

    @GetMapping("/photo")
    public String takePhoto() {
        BufferedImage photo = cameraService.takePhoto();
        return cameraService.encodePhoto(photo);
    }
}