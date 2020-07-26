package com.example.employeeserv.resource;

import com.example.employeeserv.entity.Employee;

public interface EmployeeResource {

    Employee save(Employee employee);
    Employee get(Integer employeeId);
}
