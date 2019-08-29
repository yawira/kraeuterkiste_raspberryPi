package aw.kraeuterkiste_raspberry.led.service;

import aw.kraeuterkiste_raspberry.config.GpioHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LEDService {

    private final GpioHandler gpioHandler;

    @Autowired
    public LEDService(GpioHandler gpioHandler) {
        this.gpioHandler = gpioHandler;
    }

    public void toggleLight() {
        gpioHandler.toggleLight();
    }

    public boolean getStatus() {
        return gpioHandler.isLedActive();
    }

}
