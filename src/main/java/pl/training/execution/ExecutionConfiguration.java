package pl.training.execution;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.training.execution.domain.ExecutionService;

@Configuration
public class ExecutionConfiguration {

    @Bean
    public ExecutionService executionService() {
        return new ExecutionService();
    }

}
