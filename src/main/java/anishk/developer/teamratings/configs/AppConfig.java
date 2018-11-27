package anishk.developer.teamratings.configs;

import anishk.developer.teamratings.utils.IntegerUtils;
import anishk.developer.teamratings.utils.ValidatorUtils;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing
public class AppConfig {

    @Bean
    public Mapper getMapper() {
        return new DozerBeanMapper();
    }

    @Bean
    public IntegerUtils getIntegerUtils() { return new IntegerUtils(); }

    @Bean
    public ValidatorUtils getValidatorUtils(IntegerUtils integerUtils) {
        return new ValidatorUtils(integerUtils);
    }
}
