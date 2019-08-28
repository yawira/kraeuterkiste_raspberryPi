package aw.kraeuterkiste_raspberry.moisture.controller;

import aw.kraeuterkiste_raspberry.moisture.model.MoistureDto;
import com.pi4j.io.gpio.*;
import com.pi4j.io.gpio.event.GpioPinAnalogValueChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerAnalog;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.pi4j.gpio.extension.base.AdcGpioProvider;
import com.pi4j.gpio.extension.mcp.MCP3008GpioProvider;
import com.pi4j.gpio.extension.mcp.MCP3008Pin;
import com.pi4j.io.gpio.GpioPinAnalogInput;
import com.pi4j.io.spi.SpiChannel;

import java.io.IOException;

@RestController
@RequestMapping("/moisture")
public class MoistureController {

    @GetMapping("/data")
    @ResponseBody
    public MoistureDto getMoistureData() throws IOException, InterruptedException {

        double currentValue = 0;

        GpioController gpio = GpioFactory.getInstance();

        AdcGpioProvider provider = new MCP3008GpioProvider(SpiChannel.CS0);

        GpioPinAnalogInput inputs[] = {gpio.provisionAnalogInputPin(provider, MCP3008Pin.CH2, "MyAnalogInput-CH2")};

        provider.setEventThreshold(100, inputs);
        provider.setMonitorInterval(250);

        for (GpioPinAnalogInput input : inputs) {
            currentValue = input.getValue();
        }

        GpioPinListenerAnalog listener = new GpioPinListenerAnalog() {

            private double value;

            public double getValue() {
                return this.value;
            }

            @Override
            public void handleGpioPinAnalogValueChangeEvent(GpioPinAnalogValueChangeEvent event) {
//                double value = event.getValue();
                this.value = event.getValue();
            }
        };

        gpio.addListener(listener, inputs);

        gpio.shutdown();

        return new MoistureDto(currentValue);
    }
}
