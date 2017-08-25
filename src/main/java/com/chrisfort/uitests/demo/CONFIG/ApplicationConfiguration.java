package com.chrisfort.uitests.demo.CONFIG;


import com.chrisfort.uitests.demo.utilities.WebDriverFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

/**
 * Created by cpfort on 8/25/17.
 */

@Configuration
@ComponentScan(value = "com.chrisfort.uitests.demo.CONFIG", useDefaultFilters = false,
    includeFilters = {@ComponentScan.Filter(Configuration.class)})
public class ApplicationConfiguration {

    private static final Logger LOG = LoggerFactory.getLogger(ApplicationConfiguration.class);

    private static final String PROPERTIES_LOC = "application.properties";

    @Bean
    public static PropertySourcesPlaceholderConfigurer properties() {
        PropertySourcesPlaceholderConfigurer properties = new
            PropertySourcesPlaceholderConfigurer();

        Resource[] resourceLocations = new Resource[] {new ClassPathResource(PROPERTIES_LOC)};

        properties.setLocations(resourceLocations);
        properties.setIgnoreResourceNotFound(false);
        properties.setIgnoreUnresolvablePlaceholders(true);
        properties.setLocalOverride(true);

        return properties;
    }

    @Bean
    public WebDriverFactory webDriverFactory() {
        return new WebDriverFactory();
    }
}
