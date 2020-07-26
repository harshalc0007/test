package com.example.employeeserv.service;

import com.example.employeeserv.entity.Employee;
import com.example.employeeserv.resource.EmployeeResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.HashSet;
import java.util.Set;


@Service
public class EmployeeService {

    @Autowired
    private EmployeeResource employeeResourceImpl;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * Method to dave employee
     * @param employee
     * @return
     */
    public Employee saveEmployee(final Employee employee) {
        logger.info("validating employee with name =>", employee.getName());
        Set<String> validateSet = validateEmployee(employee);
        if(!CollectionUtils.isEmpty(validateSet)) {
            throw new RuntimeException("Missing required Fields =>"+ validateSet);
        }
        return employeeResourceImpl.save(employee);
    }



    public Employee getEmployee(final Integer employeeId) {
        if(StringUtils.isEmpty(employeeId)) {
            throw new RuntimeException("Employeed Id is required");
        }
        logger.info("Fetch employee with Id =>", employeeId);
        Employee employee = employeeResourceImpl.get(employeeId);
        return employee;
    }

    private Set<String> validateEmployee(Employee employee) {
        Set<String> dataSet = new HashSet<>();
        if(StringUtils.isEmpty(employee.getName())) {
            dataSet.add("name");
        }
        if(StringUtils.isEmpty(employee.getDateOfBirth())) {
            dataSet.add("date-of-birth");
        }

        if(null == employee.getAddress() ) {
            dataSet.add("address");
        }
        if(null != employee.getAddress() && StringUtils.isEmpty(employee.getAddress().getLine1())) {
            dataSet.add("address=>line");
        }
        return dataSet;
    }
}
