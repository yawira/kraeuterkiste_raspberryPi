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

// Configure Gpio Pins for the Raspi
// This class handles all Pi4j library interaction
// @Component -> Call this class only once on program start
@Component
public class GpioHandler {

    // load initial values from config file
    @Value("${LED_PIN_NAME}")
    private String ledPinName;
    @Value("${PUMP_PIN_NAME}")
    private String pumpPinName;
    @Value("${MOIST_CHANNEL_NAME}")
    private String moistChannelName;

    @Value("${SPI_CHANNEL}")
    private int spiChannel;

    // use classes from Pi4j-Library
    private GpioController gpioController;
    private AdcGpioProvider adcGpioProvider;

    private GpioPinDigitalOutput ledOutputPin;
    private GpioPinDigitalOutput pumpOutputPin;

    private GpioPinAnalogInput moistInputPin;

    // initialize Gpio-Pins only once on program start
    // this saves time when running modules
    @PostConstruct
    public void initGpioController() throws IOException {
        gpioController = GpioFactory.getInstance();
        adcGpioProvider = new MCP3008GpioProvider(SpiChannel.getByNumber(spiChannel));

        Pin ledPin = RaspiPin.getPinByName(ledPinName);
        Pin pumpPin = RaspiPin.getPinByName(pumpPinName);
        Pin moistPin = getMoistPinByName();

        moistInputPin = gpioController.provisionAnalogInputPin(adcGpioProvider, moistPin);
        ledOutputPin = gpioController.provisionDigitalOutputPin(ledPin, PinState.HIGH);
        pumpOutputPin = gpioController.provisionDigitalOutputPin(pumpPin, PinState.HIGH);
    }

    // release handle form GPIO pins
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

    /**
     * deligate work to Pi4j-Library functions
     */
    public void toggleLight() {
        ledOutputPin.toggle();
    }

    public boolean isLedActive() {
        return ledOutputPin.getState() != PinState.HIGH;
    }

    public void togglePump() {
        pumpOutputPin.toggle();
    }

    public boolean isPumpActive() {
        return pumpOutputPin.getState() != PinState.HIGH;
    }

    public Double measureMoisture() {
        return moistInputPin.getValue();
    }
}