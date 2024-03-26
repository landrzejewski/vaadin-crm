package pl.training.execution.domain;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static pl.training.execution.domain.ExecutionStatus.CONFIRMED;

public class ExecutionService {

    public List<Execution> findByMonth(Month month) {
        if (month == Month.JANUARY) {
            return List.of(
                    Execution.builder()
                            .startDate(LocalDate.now())
                            .income(20000)
                            .costs(1000)
                            .owner("Jan Kowalski")
                            .status(CONFIRMED)
                            .training(Training.builder()
                                    .code("SPRING")
                                    .title("Spring framework")
                                    .price(2000)
                                    .durationInDays(5)
                                    .build())
                            .build(),
                    Execution.builder()
                            .startDate(LocalDate.now())
                            .income(30000)
                            .costs(2000)
                            .owner("Marek Nowak")
                            .status(CONFIRMED)
                            .training(Training.builder()
                                    .code("JS")
                                    .title("JavaScript")
                                    .price(2000)
                                    .durationInDays(3)
                                    .build())
                            .build()
            );
        } else {
            return List.of(
                    Execution.builder()
                    .startDate(LocalDate.now())
                    .income(30000)
                    .costs(2000)
                    .owner("Marek Nowak")
                    .status(CONFIRMED)
                    .training(Training.builder()
                            .code("PHP")
                            .title("Programowanie w PHP")
                            .price(1000)
                            .durationInDays(1)
                            .build())
                    .build()
            );
        }
    }

}
