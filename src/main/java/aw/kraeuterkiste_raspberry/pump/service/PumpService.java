package aw.kraeuterkiste_raspberry.pump.service;

import aw.kraeuterkiste_raspberry.pump.model.PumpDto;
import com.pi4j.io.gpio.*;
import org.springframework.stereotype.Service;

@Service
public class PumpService {

    public PumpDto togglePump() {
        boolean on = changePinState();
        return new PumpDto(on);
    }

    private boolean changePinState() {
        boolean pinStatus;

        GpioController gpio = GpioFactory.getInstance();
        Pin pin = RaspiPin.GPIO_05;
        GpioPinDigitalOutput gpioPin = gpio.provisionDigitalOutputPin(pin);

        gpioPin.toggle();
        pinStatus = (gpioPin.getState() != PinState.HIGH);

        gpio.unprovisionPin(gpioPin);
        gpio.shutdown();

        return pinStatus;
    }
}
