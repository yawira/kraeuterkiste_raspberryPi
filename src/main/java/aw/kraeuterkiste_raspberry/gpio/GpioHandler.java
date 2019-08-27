package aw.kraeuterkiste_raspberry.gpio;

import com.pi4j.io.gpio.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
public class GpioHandler {

    @Value("${LED_PIN}")
    private String ledPinName;

    private Pin ledPin;
    private GpioPinDigitalOutput ledOutputPin;

    private GpioController gpioController;

    @PostConstruct
    public void initGpioController() {
        gpioController = GpioFactory.getInstance();
        ledPin = RaspiPin.getPinByName(ledPinName);
        ledOutputPin = gpioController.provisionDigitalOutputPin(ledPin);
    }

    @PreDestroy
    public void closeGpioController() {
        gpioController.unprovisionPin(ledOutputPin);
        gpioController.shutdown();
    }

    public void toggleLight() {
        ledOutputPin.toggle();
    }

    public boolean isLedOn() {
        return ledOutputPin.getState() != PinState.HIGH;
    }

}
