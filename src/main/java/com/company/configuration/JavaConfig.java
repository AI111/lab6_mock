package com.company.configuration;

import com.company.application.ApplicationService;
import com.company.application.ApplicationServiceImpl;
import com.company.domain.EmployeeRepository;
import com.company.domain.EmployeeRepositoryImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.Scope;

/**
 * Created by sasha on 5/14/15.
 */
@Configuration
@ImportResource("beans.xml")
public class JavaConfig {

    @Bean
    @Scope(value = "singleton")
    public EmployeeRepository repository(){
        return new EmployeeRepositoryImpl();
    }

    @Bean
    @Scope(value = "singleton")
    public ApplicationService service(){
        return new ApplicationServiceImpl(repository());
    }

}
