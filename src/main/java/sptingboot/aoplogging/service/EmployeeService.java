package sptingboot.aoplogging.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import sptingboot.aoplogging.model.Employee;

import java.util.HashMap;

@Service
public class EmployeeService {
    private Logger log = LoggerFactory.getLogger(EmployeeService.class);
    private HashMap<Long, Employee> db = new HashMap<>();

    public EmployeeService() {
        db.put(1L, new Employee(1L, "Alex", "Gussin"));
        db.put(2L, new Employee(2L, "Brian", "Schultz"));
    }

    public Employee getEmployeeById(Long id) {
        return db.get(id);
    }

    public Integer countEmployees() {
        int countEmployees = db.size();
        log.info("Count Employees > 1: {}", countEmployees);
        return countEmployees;
    }
}
