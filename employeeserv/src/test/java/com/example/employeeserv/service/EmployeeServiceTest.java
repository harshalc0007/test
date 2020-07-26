package com.example.employeeserv.service;

import com.example.employeeserv.entity.Address;
import com.example.employeeserv.entity.Employee;
import com.example.employeeserv.resource.EmployeeResource;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeServiceTest {

    @InjectMocks
    private EmployeeService employeeService;

    @Mock
    private EmployeeResource employeeResourceImpl;

    @Test
    public void testSaveEmployee() {
        when(employeeResourceImpl.save(any(Employee.class))).thenReturn(getMockEmployee());
        Employee employee = employeeService.saveEmployee(getMockEmployee());
        Assert.assertNotNull(employee);
        Assert.assertEquals("Dummy", employee.getName());
    }

    @Test(expected = RuntimeException.class)
    public void testSaveEmployeeWithException() {
         Employee employee = employeeService.saveEmployee(new Employee());
    }

    @Test()
    public void testGetEmployee() {
        when(employeeResourceImpl.get(any(Integer.class))).thenReturn(getMockEmployee());
        Employee employee = employeeService.getEmployee(1);
        Assert.assertNotNull(employee);
        Assert.assertEquals("Dummy", employee.getName());

    }


    private Employee getMockEmployee() {
        Employee employee = new Employee();
        employee.setName("Dummy");
        employee.setDateOfBirth("24/02/1990");
        Address address = new Address();
        address.setLine1("data");
        employee.setAddress(address);
        return employee;
    }
}
