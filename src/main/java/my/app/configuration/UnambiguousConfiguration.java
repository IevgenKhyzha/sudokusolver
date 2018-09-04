package my.app.configuration;

import my.app.algorithmbeans.Unambiguous;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UnambiguousConfiguration {

    @Bean(name = "UNAMBIGUOUS")
    public Unambiguous getUnambiguous() {
        return new Unambiguous();
    }
}
