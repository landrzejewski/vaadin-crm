package pl.training.execution.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Training {

    private String code;
    private String title;
    private int durationInDays;
    private double price;

}
