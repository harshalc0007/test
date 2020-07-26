package com.example.employeeserv.resource;

import com.example.employeeserv.entity.Address;
import com.example.employeeserv.entity.Employee;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeResourceImplTest {

    @InjectMocks
    private EmployeeResourceImpl employeeResourceImpl;

    @Mock
    private JdbcTemplate jdbcTemplate;



    @Test
    public void testSaveEmployee() {
        Employee employee = getMockEmployee();
        employee = employeeResourceImpl.save(employee);
        Assert.assertNotNull(employee);
        Assert.assertNotNull(employee.getId());
        Assert.assertEquals("Dummy", employee.getName());
    }

    @Test
    public void testGetEmployee() {
        when(jdbcTemplate.queryForObject(anyString(),anyObject(),any(RowMapper.class))).thenReturn(new Address()).thenReturn(new Employee());
       Employee employee = employeeResourceImpl.get(1);
       Assert.assertNotNull(employee);
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
