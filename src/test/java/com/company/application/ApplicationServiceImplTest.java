package com.company.application;

import com.company.domain.Employee;
import com.company.domain.EmployeeRepository;
import org.junit.*;
import java.util.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Created by sasha on 5/23/15.
 */
public class ApplicationServiceImplTest {

    @Test
    public void testConcatUserName3() throws Exception {
        Date date = new Date();
        List<Employee> list = new ArrayList<>();
        list.add(new Employee("NAME", "LAST NAME",date ));
        list.add(new Employee("ENAME", "LAST NAME", date));
        list.add(new Employee("enE", "LAST NAME", date));
        list.add(new Employee("ENAME2_3", "LAST NAME", date));
        list.add(new Employee("E", "LAST NAME", date));
        list.add(new Employee("NAME", "LAST NAME", date));

        EmployeeRepository mockRepository = mock(EmployeeRepository.class);
        ApplicationService applicationService = new ApplicationServiceImpl(mockRepository);

        when(mockRepository.getAllEmployees()).thenReturn(list);

        applicationService.concatUserName3();
        verify(mockRepository, times(1)).getAllEmployees();

        verify(mockRepository).editEmployee(new Employee("ENAME_3", "LAST NAME", date));
        verify(mockRepository).editEmployee(new Employee("ENAME2_3_3", "LAST NAME", date));
        verify(mockRepository).editEmployee(new Employee("E_3", "LAST NAME", date));

    }

    @Test
    public void testConcatUserName3EmptyData() throws Exception {
        List<Employee> list = new ArrayList<>();

        EmployeeRepository mockRepository = mock(EmployeeRepository.class);
        ApplicationService applicationService = new ApplicationServiceImpl(mockRepository);

        when(mockRepository.getAllEmployees()).thenReturn(list);

        int i=applicationService.concatUserName3();
        verify(mockRepository, times(1)).getAllEmployees();
        verify(mockRepository,never()).editEmployee(any(Employee.class));
        assertEquals(i, 0);

    }

    @Test
    public void testConcatUserName3NoMatches() throws Exception {
        Date date = new Date();
        List<Employee> list = new ArrayList<>();
        list.add(new Employee("NAME", "LAST NAME",date ));
        list.add(new Employee("enE", "LAST NAME", date));
        list.add(new Employee("1ENAME2_3", "LAST NAME", date));
        list.add(new Employee("e", "LAST NAME", date));

        EmployeeRepository mockRepository = mock(EmployeeRepository.class);
        ApplicationService applicationService = new ApplicationServiceImpl(mockRepository);

        when(mockRepository.getAllEmployees()).thenReturn(list);

        int i=applicationService.concatUserName3();
        verify(mockRepository, times(1)).getAllEmployees();
        verify(mockRepository,never()).editEmployee(any(Employee.class));
        assertEquals(i, 0);
    }
    @Test
    public void testGetAllEmployeesWithRepeatedNames() throws Exception {
        Date date = new Date();
        List<Employee> list = new ArrayList<>();
        list.add(new Employee("NAME", "LAST NAME",date ));
        list.add(new Employee("ENAME", "LAST NAME", date));
        list.add(new Employee("ENAME", "LAST NAME", date));
        list.add(new Employee("ENAME2", "LAST NAME", date));
        list.add(new Employee("ENAME", "LAST NAME", date));
        list.add(new Employee("NAME", "LAST NAME", date));

        List<Employee> mustReturn = new ArrayList<>();
        mustReturn.add(new Employee("NAME", "LAST NAME", date));
        mustReturn.add(new Employee("NAME", "LAST NAME", date));
        mustReturn.add(new Employee("ENAME", "LAST NAME", date));
        mustReturn.add(new Employee("ENAME", "LAST NAME", date));
        mustReturn.add(new Employee("ENAME", "LAST NAME", date));

        EmployeeRepository mockRepository = mock(EmployeeRepository.class);
        when(mockRepository.getAllEmployees()).thenReturn(list);

        ApplicationService applicationService = new ApplicationServiceImpl(mockRepository);
        List ans = applicationService.getAllEmployeesWithRepeatedNames();
        assertEquals(ans, mustReturn);
        verify(mockRepository,times(1)).getAllEmployees();

    }

    @Test
    public void testGetAllEmployeesWithRepeatedNamesEmptyList() throws Exception {
        EmployeeRepository mockRepository = mock(EmployeeRepository.class);
        when(mockRepository.getAllEmployees()).thenReturn(new ArrayList<Employee>());
        ApplicationService applicationService = new ApplicationServiceImpl(mockRepository);
        List ans = applicationService.getAllEmployeesWithRepeatedNames();
        assertEquals(ans,new ArrayList<Employee>() );
        verify(mockRepository,times(1)).getAllEmployees();
    }

    @Test
    public void testGetAllEmployeesWithRepeatedNamesNoRepeat() throws Exception {
        Date date = new Date();
        List<Employee> list = new ArrayList<>();
        list.add(new Employee("NAME", "LAST NAME",date ));
        list.add(new Employee("ENAME-", "LAST NAME", date));
        list.add(new Employee("ENAME1", "LAST NAME", date));
        list.add(new Employee("ENAME2", "LAST NAME", date));
        list.add(new Employee("ENAME3", "LAST NAME", date));
        list.add(new Employee("NAME7", "LAST NAME", date));

        EmployeeRepository mockRepository = mock(EmployeeRepository.class);
        when(mockRepository.getAllEmployees()).thenReturn(list);

        ApplicationService applicationService = new ApplicationServiceImpl(mockRepository);
        List ans = applicationService.getAllEmployeesWithRepeatedNames();
        assertEquals(ans, new ArrayList<Employee>());
        verify(mockRepository,times(1)).getAllEmployees();

    }
}