package aw.kraeuterkiste_raspberry.config;

import com.hopding.jrpicam.RPiCamera;
import com.hopding.jrpicam.exceptions.FailedToRunRaspistillException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.IOException;


@Component
public class Camera {
    @Value("${IMG_WIDTH}")
    private int imgWidth;

    @Value("${IMG_HEIGHT}")
    private int imgHeight;

    private final RPiCamera camera;

    public Camera() throws FailedToRunRaspistillException {
        this.camera = new RPiCamera();
        camera.setToDefaults();
    }

    public BufferedImage takePhoto() {
        try {
            return camera.takeBufferedStill(imgWidth, imgHeight);
        } catch (IOException | InterruptedException e) {
            return null;
        }
    }
}