package aw.kraeuterkiste_raspberry;

import aw.kraeuterkiste_raspberry.config.Camera;
import aw.kraeuterkiste_raspberry.config.GpioHandler;
import aw.kraeuterkiste_raspberry.moisture.controller.MoistureController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class KraeuterkisteRaspberryApplicationTests {

    @MockBean
    GpioHandler gpioHandler;

    @MockBean
    Camera camera;

    @MockBean
    MoistureController moistureController;

    @Test
    public void contextLoads() {
    }

}
