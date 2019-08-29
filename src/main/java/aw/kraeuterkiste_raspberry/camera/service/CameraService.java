package aw.kraeuterkiste_raspberry.camera.service;

import aw.kraeuterkiste_raspberry.config.Camera;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;

@Service
public class CameraService {

    @Value("${IMG_FORMAT}")
    private String imgFormat;

    private final Camera camera;

    @Autowired
    public CameraService(Camera camera) {
        this.camera = camera;
    }

    public BufferedImage takePhoto() {
        return camera.takePhoto();
    }

    public byte[] convPhotoToByteStream(BufferedImage photo) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            ImageIO.write(photo, imgFormat, baos);
        } catch (IOException ignored) { }
        return baos.toByteArray();
    }

    public String encodePhoto(BufferedImage photo) {
        byte[] convPhoto = convPhotoToByteStream(photo);
        return Base64.getEncoder().encodeToString(convPhoto);
    }
}