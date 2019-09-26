package aw.kraeuterkiste_raspberry.moisture.service;

import aw.kraeuterkiste_raspberry.config.GpioHandler;
import aw.kraeuterkiste_raspberry.moisture.model.MoistureDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;

@Service
@EnableScheduling
public class MoistureService {

    private final GpioHandler gpioHandler;
    private final RestTemplate backendRestTemplate;

    @Autowired
    public MoistureService(GpioHandler gpioHandler, RestTemplate backendRestTemplate) {
        this.gpioHandler = gpioHandler;
        this.backendRestTemplate = backendRestTemplate;
    }

    // Annotation sends POST/ request every 10 seconds to backend via RestTemplate.
    @Scheduled(fixedRate = 10000)
    public void measureAndBroadcastData() {
        MoistureDto moistureDto = new MoistureDto(LocalDateTime.now(), gpioHandler.measureMoisture());
        backendRestTemplate.postForObject("/moisture/data", moistureDto, MoistureDto.class);
    }

}