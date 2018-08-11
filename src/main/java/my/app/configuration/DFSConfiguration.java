package my.app.configuration;

import my.app.configuration.beans.DepthFirstAlgorithm;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DFSConfiguration {

    @Bean(name = "DFS")
    public DepthFirstAlgorithm getDepthFirstAlgorithm() {
        return new DepthFirstAlgorithm();
    }
}
