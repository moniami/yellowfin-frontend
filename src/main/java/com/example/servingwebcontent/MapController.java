package com.example.servingwebcontent;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import java.io.*;
import java.io.BufferedReader;
import org.springframework.ui.Model;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.IOException;
import java.util.Properties;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MapController {
	String configFilePath = "src/main/resources/config.ini";
	@GetMapping("/map")
	public String mapping(Model model) throws IOException {

		try {
			FileInputStream propsInput = new FileInputStream(configFilePath);
			Properties prop = new Properties();
			prop.load(propsInput);
			String BACKEND_URL;
			BACKEND_URL = prop.getProperty("BACKEND_URL");
			URL url = new URL("http://"+BACKEND_URL +":8081/currentUser");
			try {
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				conn.setRequestMethod("GET");

				if (conn.getResponseCode() != 200) {
					model.addAttribute("key", "Guest");
					return "map";
				}
				else {
					BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
					StringBuilder jsonString = new StringBuilder();
					String readAPIResponse = "";
					while((readAPIResponse = br.readLine()) != null){
						jsonString.append(readAPIResponse);
					}
					JSONObject jsonObj = new JSONObject(jsonString.toString());
					System.out.println(jsonString);
					System.out.println("---------------------------");
					System.out.println(jsonObj);
					model.addAttribute("key", (String) jsonObj.get("user"));
					return  "map";
				}

			} catch (Exception e){
				System.out.println(e);
				model.addAttribute("key", "User");
				return  "map";
			}

		} catch (FileNotFoundException e) {
			System.out.println(e);
			return "map";

		} catch (IOException e) {
			System.out.println(e);
			return "map";
		}
	}

}
