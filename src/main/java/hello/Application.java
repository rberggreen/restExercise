package hello;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {
    private static TestModel testModel;

    public static void main(String[] args) {
        testModel = new TestModel();
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public TestModel testModel() {
        return testModel;
    }


}