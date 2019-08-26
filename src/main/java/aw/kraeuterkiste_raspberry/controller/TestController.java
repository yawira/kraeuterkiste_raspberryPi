package aw.kraeuterkiste_raspberry.controller;

import com.pi4j.io.gpio.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/test")
    public void test() {
        toogle_light();
    }

    private void toogle_light() {
        GpioController gpio = GpioFactory.getInstance();
        Pin pin = RaspiPin.GPIO_04;
        GpioPinDigitalOutput gpioPin = gpio.provisionDigitalOutputPin(pin);

        gpioPin.toggle();
        gpio.unprovisionPin(gpioPin);

        gpio.shutdown();
    }
}
