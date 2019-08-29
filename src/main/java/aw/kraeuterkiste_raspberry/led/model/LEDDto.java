package aw.kraeuterkiste_raspberry.led.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LEDDto {
    private LocalDateTime dateTime;
    private boolean active;
}
