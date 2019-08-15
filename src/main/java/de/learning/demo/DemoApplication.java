package de.learning.demo;

import de.learning.demo.services.AuthorizationTokenService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@SpringBootApplication
@EnableTransactionManagement
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
        //refreshTokenEvery5Min();
    }
    public static void refreshTokenEvery5Min() {
        try {
            while (true) {
                AuthorizationTokenService.setAuthTokenBeanValue();
                Thread.sleep(3000 * 1000);//5 min
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
