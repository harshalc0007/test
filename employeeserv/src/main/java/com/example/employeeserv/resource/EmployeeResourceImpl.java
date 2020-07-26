package com.example.employeeserv.resource;

import com.example.employeeserv.entity.Address;
import com.example.employeeserv.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

@Repository
public class EmployeeResourceImpl implements EmployeeResource {
    private static Integer EMP_ID =1;
    private static Integer ADD_ID = 101;
    @Autowired
    JdbcTemplate jdbcTemplate;



    public Employee save(Employee employee) {

        Address address = employee.getAddress();
        jdbcTemplate.update("insert into address (id, line1,line2,city,state,country,zip_code,emp_id) " + "values(?, ?, ?, ?, ?, ?, ?, ?)",
                new Object[] {
                        ADD_ID , address.getLine1(),address.getLine2(),address.getCity(),address.getState(),address.getCountry(),address.getZipCode(),EMP_ID
                });
        jdbcTemplate.update("insert into employee (id, name,dateOfBirth) " + "values(?, ?, ?)",
                new Object[] {
                       EMP_ID , employee.getName(), employee.getDateOfBirth()
                });
        employee.setId(EMP_ID);
        ++EMP_ID;
        ++ADD_ID;
        return  employee;

    }

    @Override
    public Employee get(Integer employeeId) {
        Optional<Employee> employeeOptional = null;
        Optional<Address> addressOptional = null;
        Employee employee = null;
        try {
            addressOptional = Optional.of(jdbcTemplate.queryForObject("select * from address where emp_id=?", new Object[]{
                            employeeId
                    },
                    new BeanPropertyRowMapper<Address>(Address.class)));

            employeeOptional = Optional.of(jdbcTemplate.queryForObject("select * from employee where id=?", new Object[]{
                            employeeId
                    },
                    new BeanPropertyRowMapper<Employee>(Employee.class)));
        } catch (Exception exception) {
            exception.printStackTrace();
            return employee;
        }

        if(employeeOptional.isPresent()) {
            employee = employeeOptional.get();
            employee.setAddress(addressOptional.get());
        }
     return employee;
    }

    class EmployeeRowMapper implements RowMapper< Employee > {
        @Override
        public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
            Employee employee = new Employee();
            employee.setId(rs.getInt("id"));
            employee.setName(rs.getString("name"));
            employee.setDateOfBirth(rs.getString("date-of-birth"));

            return employee;
        }
    }

    class AddressRowMapper implements RowMapper< Address > {
        @Override
        public Address mapRow(ResultSet rs, int rowNum) throws SQLException {
            Address address = new Address();
            address.setLine1(rs.getString("line1"));
            address.setLine2(rs.getString("line2"));
            address.setCity(rs.getString("city"));
            address.setState(rs.getString("state"));
            address.setCountry(rs.getString("country"));
            address.setZipCode(rs.getInt("zipCode"));
            return address;
        }
    }
}
