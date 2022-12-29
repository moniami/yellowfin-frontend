package com.example.servingwebcontent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

@SpringBootApplication
public class ServingWebContentApplication {

    public static void main(String[] args) throws FileNotFoundException {

        String configFilePath = "src/main/resources/config.ini";
        try {
            FileInputStream propsInput = new FileInputStream(configFilePath);
            Properties prop;
            prop = new Properties();
            prop.load(propsInput);

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        SpringApplication.run(ServingWebContentApplication.class, args);
    }

}
