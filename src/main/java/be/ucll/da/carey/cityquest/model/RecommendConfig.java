package be.ucll.da.carey.cityquest.model;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.net.URI;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties
@Data
public class RecommendConfig {
    private URI recommendserver;
}
