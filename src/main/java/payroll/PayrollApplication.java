package payroll;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class PayrollApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(PayrollApplication.class, args);
    }


}
