package com.example.servingwebcontent;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

@SpringBootApplication
public class ServingWebContentApplication {

    public static void main(String[] args) throws IOException {

        SpringApplication.run(ServingWebContentApplication.class, args);
    }

}
