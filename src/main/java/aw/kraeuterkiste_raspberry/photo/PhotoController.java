package aw.kraeuterkiste_raspberry.photo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

@RestController
public class PhotoController {
    @GetMapping("/upload")
    public String uploadPicture() {
        Path path = Paths.get("/home/pi/bild1.jpg");

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
