package my.app.configuration;

import my.app.algorithmbeans.MarinaAlgorithm;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MarinaConfiguration {

    @Bean(name = "MARINA")
    public MarinaAlgorithm getMarinaAlgorithm() {
        return new MarinaAlgorithm();
    }
}
