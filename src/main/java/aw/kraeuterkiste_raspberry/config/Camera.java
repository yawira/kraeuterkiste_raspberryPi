package aw.kraeuterkiste_raspberry.config;

import com.hopding.jrpicam.RPiCamera;
import com.hopding.jrpicam.exceptions.FailedToRunRaspistillException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.IOException;

// Set initial values for photos
// @Component -> Call this class only once on program start
@Component
public class Camera {
    // load initial values from config file
    @Value("${IMG_WIDTH}")
    private int imgWidth;

    @Value("${IMG_HEIGHT}")
    private int imgHeight;

    private final RPiCamera camera;

    // initialize camera from RPiCamera-Library
    public Camera() throws FailedToRunRaspistillException {
        this.camera = new RPiCamera();
        camera.setToDefaults();
    }

    // provide functionality to take a photo
    public BufferedImage takePhoto() {
        try {
            return camera.takeBufferedStill(imgWidth, imgHeight);
        } catch (IOException | InterruptedException e) {
            return null;
        }
    }
}