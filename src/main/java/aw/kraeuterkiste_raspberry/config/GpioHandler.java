package aw.kraeuterkiste_raspberry.config;

import com.pi4j.gpio.extension.base.AdcGpioProvider;
import com.pi4j.gpio.extension.mcp.MCP3008GpioProvider;
import com.pi4j.gpio.extension.mcp.MCP3008Pin;
import com.pi4j.io.gpio.*;
import com.pi4j.io.spi.SpiChannel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.IOException;

@Component
public class GpioHandler {

    @Value("${LED_PIN_NAME}")
    private String ledPinName;
    @Value("${PUMP_PIN_NAME}")
    private String pumpPinName;
    @Value("${MOIST_CHANNEL_NAME}")
    private String moistChannelName;

    @Value("${SPI_CHANNEL}")
    private int spiChannel;

    private GpioController gpioController;
    private AdcGpioProvider adcGpioProvider;

    private GpioPinDigitalOutput ledOutputPin;
    private GpioPinDigitalOutput pumpOutputPin;

    private GpioPinAnalogInput moistInputPin;

    @PostConstruct
    public void initGpioController() throws IOException {
        gpioController = GpioFactory.getInstance();
        adcGpioProvider = new MCP3008GpioProvider(SpiChannel.getByNumber(spiChannel));

        Pin ledPin = RaspiPin.getPinByName(ledPinName);
        Pin pumpPin = RaspiPin.getPinByName(pumpPinName);
        Pin moistPin = getMoistPinByName();

        moistInputPin = gpioController.provisionAnalogInputPin(adcGpioProvider, moistPin);
        ledOutputPin = gpioController.provisionDigitalOutputPin(ledPin);
        pumpOutputPin = gpioController.provisionDigitalOutputPin(pumpPin);
    }

    @PreDestroy
    public void shutdownGpioController() {
        gpioController.unprovisionPin(ledOutputPin, pumpOutputPin, moistInputPin);
        gpioController.shutdown();
    }

    private Pin getMoistPinByName() {
        for(Pin pin : MCP3008Pin.ALL) {
            if(pin.getName().equals(moistChannelName)) return pin;
        }
        return null;
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

    public Double measureMoisture() {
        return moistInputPin.getValue();
    }
}


