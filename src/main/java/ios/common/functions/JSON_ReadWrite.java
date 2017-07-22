package ios.common.functions;

import java.io.FileReader;
import java.io.FileWriter;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
/**
 *  @author sambeetmohapatra
 *
 * Returns JSON File Data
 */
public class JSON_ReadWrite {

	public static String Read_Json_Data(String key) throws Exception {
		
		JSONParser parser = new JSONParser();
		
		 Object obj = parser.parse(new FileReader(Automation_Constants.PAGE_OBJECTS_JSON_FILE));
		 JSONObject jsonObject = (JSONObject) obj;
		  return (String) jsonObject.get(key);

	}
	
	@SuppressWarnings("unchecked")
	public static void Write_Json_Data(String key , String value,String FilePath) throws Exception{
		JSONObject obj = new JSONObject();
		//Write data to json file
		obj.put(key	, value);
		
		FileWriter fwrite = new FileWriter(FilePath);
		fwrite.write(obj.toJSONString());
		fwrite.flush();
		fwrite.close();
		
	}

}
