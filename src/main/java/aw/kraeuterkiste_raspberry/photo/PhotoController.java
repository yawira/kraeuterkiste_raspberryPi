package aw.kraeuterkiste_raspberry.photo;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hopding.jrpicam.RPiCamera;
import com.hopding.jrpicam.exceptions.FailedToRunRaspistillException;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import java.util.Base64;

@RestController
public class PhotoController {

    @Value("${IMG_WIDTH}")
    private int imgWidth;

    @Value("${IMG_HEIGHT}")
    private int imgHeight;

    @Value("${IMG_FORMAT}")
    private String imgFormat;

    @GetMapping("/upload")
    public String uploadPicture() {

        byte[] picAsBytes = new byte[]{};

        try {
            RPiCamera piCamera = new RPiCamera();

            piCamera.setToDefaults();

            BufferedImage pic = piCamera.takeBufferedStill( imgWidth, imgHeight);

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(pic, imgFormat, baos);
            picAsBytes = baos.toByteArray();

        } catch (FailedToRunRaspistillException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return Base64.getEncoder().encodeToString(picAsBytes);
    }
}