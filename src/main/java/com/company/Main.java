package com.company;

import com.company.application.ApplicationService;
import com.company.configuration.JavaConfig;
import com.company.domain.Employee;
import com.company.domain.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import java.util.List;

public class Main {
    final static Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        logger.info("app start");
        ApplicationContext context = new AnnotationConfigApplicationContext(JavaConfig.class);
//        EmployeeRepository repository = (EmployeeRepository) context.getBean("repository");
//
//        repository.createEmployee(new Employee("NAME","LAST NAME",new Date()));
//        repository.createEmployee(new Employee("ENAME","LAST NAME",new Date()));
//        repository.createEmployee(new Employee("ENAME","LAST NAME",new Date()));
//        repository.createEmployee(new Employee("ENAME2","LAST NAME",new Date()));
//        repository.createEmployee(new Employee("ENAME3", "LAST NAME", new Date()));
//        repository.createEmployee(new Employee("NAME", "LAST NAME", new Date()));
        ApplicationService applicationService = (ApplicationService)context.getBean("service");
        System.out.println(" Добавление к сотрудникам с именем на 'Е' _3\n");
//        System.out.println(repository.getAllEmployees());
        try{
            applicationService.concatUserName3();

        }catch (Exception e){
            logger.error("applicationService.concatUserName3() {}",e.getStackTrace());
        }
//        System.out.println(repository.getAllEmployees());
        System.out.println("\n Нахождение сотрудников с повторяющимся именем");
//        System.out.println(repository.getAllEmployees());

        List list =null;
        try{
            list =applicationService.getAllEmployeesWithRepeatedNames();
        }catch (Exception e){
            logger.error("applicationService.getAllEmployeesWithRepeatedNames() {}",e.getStackTrace());
        }

        System.out.println(list);
        logger.info("app finish");
    }
}
