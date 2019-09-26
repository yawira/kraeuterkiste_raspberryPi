package aw.kraeuterkiste_raspberry.camera.controller;

import aw.kraeuterkiste_raspberry.camera.service.CameraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.image.BufferedImage;


// map url /camera/.. to this controller

@RestController
@RequestMapping("/camera")
public class CameraController {

    // inject cameraService to interact with Pi Camera
    private final CameraService cameraService;

    @Autowired
    public CameraController(CameraService cameraService) {
        this.cameraService = cameraService;
    }

    // handle photo-request
    @GetMapping("/photo")
    public String takePhoto() {
        BufferedImage photo = cameraService.takePhoto();
        return cameraService.encodePhoto(photo);
    }
}