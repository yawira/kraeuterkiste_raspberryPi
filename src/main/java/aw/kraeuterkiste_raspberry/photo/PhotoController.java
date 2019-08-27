package aw.kraeuterkiste_raspberry.photo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import uk.co.caprica.picam.*;
import uk.co.caprica.picam.enums.AutomaticWhiteBalanceMode;
import uk.co.caprica.picam.enums.Encoding;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

import static uk.co.caprica.picam.CameraConfiguration.cameraConfiguration;

@RestController
public class PhotoController {
    @GetMapping("/upload")
    public String uploadPicture() {

        CameraConfiguration config = cameraConfiguration()
                .width(1920)
                .height(1080)
                .automaticWhiteBalance(AutomaticWhiteBalanceMode.AUTO)
                .encoding(Encoding.JPEG)
                .quality(85);

        try (Camera camera = new Camera(config)) {
            camera.takePicture(new FilePictureCaptureHandler(new File("photo1.jpg")));
        }
        catch (CaptureFailedException e) {
            e.printStackTrace();
        } catch (CameraException e) {
            e.printStackTrace();
        }


        Path path = Paths.get("/home/pi/photo1.jpg");
        //Path path = Paths.get("/home/pi/bild1.jpg");

        byte[] picture = null;
        // IOException muss gefangen werden, um die "readAllBytes" Methode zu verwenden
        try {
            picture = Files.readAllBytes(path);
        } catch (final IOException e) {
        }

        String encodedImage = Base64.getEncoder().encodeToString(picture);
        //String encodedImage = Base64.encode(picture);

        return encodedImage;
    }
}
