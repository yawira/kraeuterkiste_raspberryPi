package aw.kraeuterkiste_raspberry.moisture.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MoistureDto {
    private LocalDateTime dateTime;
    private Double percentage;
}
