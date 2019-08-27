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
    @Value("${PUMP_PIN}")
    private String pumpPinName;

    private Pin ledPin;
    private Pin pumpPin;
    private GpioPinDigitalOutput ledOutputPin;
    private GpioPinDigitalOutput pumpOutputPin;

    private GpioController gpioController;

    @PostConstruct
    public void initGpioController() {
        gpioController = GpioFactory.getInstance();
        ledPin = RaspiPin.getPinByName(ledPinName);
        pumpPin = RaspiPin.getPinByName(pumpPinName);
        ledOutputPin = gpioController.provisionDigitalOutputPin(ledPin);
        pumpOutputPin = gpioController.provisionDigitalOutputPin(pumpPin);
    }

    @PreDestroy
    public void closeGpioController() {
        gpioController.unprovisionPin(ledOutputPin);
        gpioController.unprovisionPin(pumpOutputPin);
        gpioController.shutdown();
    }

    public void toggleLight() {
        ledOutputPin.toggle();
    }

    public boolean isLedOn() {
        return ledOutputPin.getState() != PinState.HIGH;
    }

    public void togglePump() {
        pumpOutputPin.toggle();
    }

    public boolean isPumpOn() {
        return pumpOutputPin.getState() != PinState.HIGH;
    }

}
