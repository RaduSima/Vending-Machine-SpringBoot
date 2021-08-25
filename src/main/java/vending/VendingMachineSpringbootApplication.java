package vending;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class VendingMachineSpringbootApplication {

    public static void main(String[] args) {
        SpringApplication.run(VendingMachineSpringbootApplication.class, args);
        System.out.println("IT'S RUNNING!");
    }

}
