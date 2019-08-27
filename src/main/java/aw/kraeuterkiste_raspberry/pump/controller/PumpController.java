package aw.kraeuterkiste_raspberry.pump.controller;

import aw.kraeuterkiste_raspberry.pump.model.PumpDto;
import aw.kraeuterkiste_raspberry.pump.service.PumpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pump")
public class PumpController {

    private PumpService pumpService;

    @Autowired
    public PumpController(PumpService pumpService) {
        this.pumpService = pumpService;
    }

    @GetMapping("/toggle")
    @ResponseBody
    public PumpDto toggle() {
        return pumpService.togglePump();
    }

}
