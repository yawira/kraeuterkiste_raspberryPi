package aw.kraeuterkiste_raspberry;

import aw.kraeuterkiste_raspberry.gpio.GpioHandler;
import aw.kraeuterkiste_raspberry.moisture.controller.MoistureController;
import aw.kraeuterkiste_raspberry.moisture.service.MoistureService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class KraeuterkisteRaspberryApplicationTests {

    @MockBean
    GpioHandler gpioHandler;

    @MockBean
    MoistureService moistureService;

    @InjectMocks
    MoistureController moistureController;

    @Test
    public void contextLoads() {
    }

}
