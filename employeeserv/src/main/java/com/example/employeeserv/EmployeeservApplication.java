package com.example.employeeserv;

import com.example.employeeserv.entity.Address;
import com.example.employeeserv.entity.Employee;
import com.example.employeeserv.resource.EmployeeResourceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EmployeeservApplication implements CommandLineRunner {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private EmployeeResourceImpl employeeRepository;

	public static void main(String[] args) {
		SpringApplication.run(EmployeeservApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		Employee employee = new Employee();
		employee.setId(10011);
		employee.setName("Harshal");
		Address address = new Address();
		address.setLine1("line1");
		address.setLine2("line2");
		address.setCity("varanagn");
		address.setState("MH");
		address.setCountry("IN");
		address.setZipCode(425305);
		employee.setAddress(address);
		logger.info("Inserting -> {}", employeeRepository.save(employee));

	}
}
