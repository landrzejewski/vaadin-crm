package pl.training.execution.domain;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Execution {

    private LocalDate startDate;
    private ExecutionStatus status;
    @NotBlank
    private Training training;
    private String owner;
    private double income;
    private double costs;

    public double getIncomePerDay() {
        return income / training.getDurationInDays();
    }

}
