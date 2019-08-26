package aw.kraeuterkiste_raspberry.led.service;

import aw.kraeuterkiste_raspberry.led.model.LEDDto;
import com.pi4j.io.gpio.*;
import org.springframework.stereotype.Service;

@Service
public class LEDService {

    public LEDDto toggleLight() {
        boolean on = changePinState();
        return new LEDDto(on);
    }

    private boolean changePinState() {
        boolean pinStatus;

        GpioController gpio = GpioFactory.getInstance();
        Pin pin = RaspiPin.GPIO_04;
        GpioPinDigitalOutput gpioPin = gpio.provisionDigitalOutputPin(pin);

        gpioPin.toggle();
        pinStatus = (gpioPin.getState() != PinState.HIGH);

        gpio.unprovisionPin(gpioPin);
        gpio.shutdown();

        return pinStatus;
    }
}
