package sptingboot.aoplogging;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import sptingboot.aoplogging.service.EmployeeService;

@SpringBootApplication
public class AopLoggingApplication {

    private final EmployeeService employeeManager;

    @Autowired
    public AopLoggingApplication(EmployeeService employeeManager) {
        this.employeeManager = employeeManager;
    }

    public static void main(String[] args) {
        SpringApplication.run(AopLoggingApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {
            System.out.println(employeeManager.getEmployeeById(1L));
            System.out.println(employeeManager.countEmployees());
        };
    }

}
