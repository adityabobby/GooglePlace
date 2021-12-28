package resources;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;

public class Utils {
	PrintStream log = null;
	FileReader fr = null;

	public RequestSpecification requestSpecification() {
		try {
			log = new PrintStream(new FileOutputStream("logging.text"));
		} catch (FileNotFoundException fe) {
			System.out.println(fe);
		}
		RequestSpecification req = new RequestSpecBuilder().setBaseUri(getProperty("baseURI"))
				.addQueryParam("key", "qaclick123")
				.addFilter(RequestLoggingFilter.logRequestTo(log)).addFilter(ResponseLoggingFilter.logResponseTo(log))
				.build();
		return req;
	}

	public static JsonPath getJsonPath(String response) {
		return new JsonPath(response);
	}

	public String getProperty(String key) {
		Properties p = new Properties();
		try {
			fr = new FileReader("./src/test/java/resources/global.properties");
		} catch (FileNotFoundException fe) {
			System.out.println(fe);

		}
		if (fr != null) {
			try {
				p.load(fr);
			} catch (IOException e) {
				System.out.println(e);
			}
		}
		System.out.println(p.getProperty(key));
		return p.getProperty(key);

	}
	
	public String fetchValueResponse(JsonPath js, String key)
	{
		return js.getString(key);
	}

}
