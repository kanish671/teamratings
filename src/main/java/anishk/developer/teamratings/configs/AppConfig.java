package anishk.developer.teamratings.configs;

import anishk.developer.teamratings.utils.DateUtils;
import anishk.developer.teamratings.utils.IntegerUtils;
import anishk.developer.teamratings.utils.LongUtils;
import anishk.developer.teamratings.utils.ValidatorUtils;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableJpaAuditing
@EnableAutoConfiguration
public class AppConfig {

    @Bean
    public Mapper getMapper() {
        return new DozerBeanMapper();
    }

    @Bean
    public IntegerUtils getIntegerUtils() { return new IntegerUtils(); }

    @Bean
    public LongUtils getLongUtils() { return new LongUtils(); }

    @Bean
    public DateUtils getDateUtils() { return new DateUtils(); }

    @Bean
    public ValidatorUtils getValidatorUtils(IntegerUtils integerUtils, LongUtils longUtils, DateUtils dateUtils) {
        return new ValidatorUtils(integerUtils, longUtils, dateUtils);
    }

    @Bean
    ServletWebServerFactory servletWebServerFactory(){
        return new TomcatServletWebServerFactory();
    }
}
