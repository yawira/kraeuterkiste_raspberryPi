package aw.kraeuterkiste_raspberry.gpio;

import com.pi4j.io.gpio.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class GpioHandler {

    @Value("${LED_PIN}")
    private String ledPinName;
    @Value("${PUMP_PIN}")
    private String pumpPinName;

    private Pin ledPin;
    private Pin pumpPin;
    private Pin moistPin;

    private GpioPinDigitalOutput ledOutputPin;
    private GpioPinDigitalOutput pumpOutputPin;
    private GpioPinDigitalOutput moistOutputPin;

    private GpioController gpioController;

//    @PostConstruct
//    public void initGpioController() {
//        gpioController = GpioFactory.getInstance();
//        ledPin = RaspiPin.getPinByName(ledPinName);
//        pumpPin = RaspiPin.getPinByName(pumpPinName);
//        ledOutputPin = gpioController.provisionDigitalOutputPin(ledPin);
//        pumpOutputPin = gpioController.provisionDigitalOutputPin(pumpPin);
//    }
//
//    @PreDestroy
//    public void closeGpioController() {
//        gpioController.unprovisionPin(ledOutputPin);
//        gpioController.unprovisionPin(pumpOutputPin);
//        gpioController.shutdown();
//    }

    public void toggleLight() {
        gpioController = GpioFactory.getInstance();
        ledPin = RaspiPin.GPIO_04;
        ledOutputPin = gpioController.provisionDigitalOutputPin(ledPin);

        ledOutputPin.toggle();

        gpioController.unprovisionPin(ledOutputPin);
        gpioController.shutdown();
    }

    public boolean isLedOn() {
        gpioController = GpioFactory.getInstance();
        ledPin = RaspiPin.GPIO_04;
        ledOutputPin = gpioController.provisionDigitalOutputPin(ledPin);

        boolean ledOn = ledOutputPin.getState() != PinState.HIGH;

        gpioController.unprovisionPin(ledOutputPin);
        gpioController.shutdown();

        return ledOn;
    }

    public void togglePump() {
        gpioController = GpioFactory.getInstance();
        pumpPin = RaspiPin.GPIO_05;
        pumpOutputPin = gpioController.provisionDigitalOutputPin(pumpPin);

        pumpOutputPin.toggle();

        gpioController.unprovisionPin(pumpOutputPin);
        gpioController.shutdown();
    }

    public boolean isPumpOn() {
        gpioController = GpioFactory.getInstance();
        pumpPin = RaspiPin.GPIO_05;
        pumpOutputPin = gpioController.provisionDigitalOutputPin(pumpPin);

        boolean pumpOn = pumpOutputPin.getState() != PinState.HIGH;

        gpioController.unprovisionPin(pumpOutputPin);
        gpioController.shutdown();

        return pumpOn;
    }
}


