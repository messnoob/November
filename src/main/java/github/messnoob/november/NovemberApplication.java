package github.messnoob.november;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class NovemberApplication {

    public static void main(String[] args) {
        SpringApplication.run(NovemberApplication.class, args);
    }

}
