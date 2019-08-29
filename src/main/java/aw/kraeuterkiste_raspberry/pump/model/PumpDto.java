package aw.kraeuterkiste_raspberry.pump.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PumpDto {
    private LocalDateTime dateTime;
    private boolean active;
}
