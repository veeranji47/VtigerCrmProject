package pratice.datadriventesting;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ReadDataFromJsonFile {

	public static void main(String[] args) throws Exception, IOException, ParseException {
		JSONParser parser = new JSONParser();
		 Object object = parser.parse(new FileReader("./src/test/resources/commonData.json"));
		 JSONObject map = (JSONObject) object;
		 
		 System.out.println(map.get("browser"));
		 System.out.println(map.get("url"));
	}

}
