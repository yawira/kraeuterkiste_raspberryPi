package aw.kraeuterkiste_raspberry;

import aw.kraeuterkiste_raspberry.gpio.GpioHandler;
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

    @Test
    public void contextLoads() {
    }

}
