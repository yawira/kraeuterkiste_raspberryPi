package aw.kraeuterkiste_raspberry.led.controller;

import aw.kraeuterkiste_raspberry.led.model.LEDDto;
import aw.kraeuterkiste_raspberry.led.service.LEDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/led")
public class LEDController {

    private LEDService ledService;

    @Autowired
    public LEDController(LEDService ledService) {
        this.ledService = ledService;
    }

    @GetMapping("/toggle")
    @ResponseBody
    public LEDDto toggle() {
        return ledService.toggleLight();
    }

}
