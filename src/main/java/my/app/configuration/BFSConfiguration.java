package my.app.configuration;

import my.app.configuration.beans.BreadthFirstAlgorithm;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BFSConfiguration {

    @Bean(name = "BFS")
    public BreadthFirstAlgorithm getBFS() {
        return new BreadthFirstAlgorithm();
    }
}
