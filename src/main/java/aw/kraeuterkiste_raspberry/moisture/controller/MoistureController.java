package aw.kraeuterkiste_raspberry.moisture.controller;
import aw.kraeuterkiste_raspberry.moisture.service.MoistureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@RestController
@RequestMapping("/moisture")
public class MoistureController {

    private final MoistureService moistureService;

    @Autowired
    public MoistureController(MoistureService moistureService) {
        this.moistureService = moistureService;
    }


    @PostConstruct
    public void startBroadcastingMeasuredData() {
        moistureService.measureAndBroadcastData();
    }

}
