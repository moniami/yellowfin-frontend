package com.example.servingwebcontent;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.*;
import java.net.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.IOException;
import java.util.Properties;


@Controller
public class MapController {
	String configFilePath = "src/main/resources/config.ini";
	@GetMapping("/map")
	public String greeting() throws IOException {

		try {
			FileInputStream propsInput = new FileInputStream(configFilePath);
			Properties prop = new Properties();
			prop.load(propsInput);

			String BACKEND_URL;
			BACKEND_URL = prop.getProperty("BACKEND_URL");
			URL url = new URL("http://"+BACKEND_URL +":8080/currentUser");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");

			if (conn.getResponseCode() != 200) {
				error(conn.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
			String response;
			while ((response = br.readLine()) != null) {
				System.out.println(response);
			}
			return response;

		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);

		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@GetMapping("/error")
	public Integer error(int error){
		return error;
	}

}
