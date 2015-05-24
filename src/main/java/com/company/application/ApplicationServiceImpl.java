package com.company.application;

import com.company.domain.Employee;
import com.company.domain.EmployeeRepository;
import com.company.domain.EmployeeRepositoryImpl;
import org.apache.log4j.LogManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by sasha on 4/28/15.
 */
public class ApplicationServiceImpl implements ApplicationService {
    static final Logger logger = LoggerFactory.getLogger(ApplicationService.class);


    EmployeeRepository repository;

    @Autowired
    public ApplicationServiceImpl(EmployeeRepository repository) {
        this.repository = repository;
    }

    public int concatUserName3() {
        logger.trace("concatUserName3 begin");
        int count=0;
        List<Employee> list= repository.getAllEmployees();
        for(Employee employee:list){
            if(employee.getName().startsWith("E")){
                employee.setName(employee.getName()+"_3");
                repository.editEmployee(employee);
                count++;
            }
        }
        logger.trace("concatUserName3 end");
        logger.debug("concatUserName3() find {} element",count);
        return count;
    }

    public List<Employee> getAllEmployeesWithRepeatedNames() {
        //полная хуйня
        //но мозг уже отрубился
        logger.trace("getAllEmployeesWithRepeatedNames bigin");
        List<Employee> answer = new ArrayList<Employee>();
        List<Employee> list = repository.getAllEmployees();

        boolean[] aded = new boolean[list.size()];
        boolean repeat=false;
        Arrays.fill(aded,false);
        for (int i = 0; i < list.size() ; i++) {
            for (int j = 0; j < list.size(); j++) {
                if(i!=j&&!aded[i]&&list.get(i).getName().equals(list.get(j).getName())){
                    if(!repeat)answer.add(list.get(i));
                    repeat=true;
                    answer.add(list.get(j));
                    aded[j]=true;

                }
            }
            repeat =false;
        }
        logger.trace("getAllEmployeesWithRepeatedNames end");
        logger.debug("getAllEmployeesWithRepeatedNames() return {} ",list);
        return answer;
    }
}
