package utilities;

import com.jayway.jsonpath.JsonPath;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.FileReader;

public class JsonUtils {
    private static final String JSON_FILE_PATH = "src/test/resources";
    String josnReader;
    String jsonFileName;

    public JsonUtils(String jsonFileName) {
        this.jsonFileName = jsonFileName;
        try {
            JSONParser data = (JSONParser) new JSONParser().parse(new FileReader(JSON_FILE_PATH + jsonFileName));
            //josnReader = data.toJSONString();
        } catch ( Exception e ) {
            LogsUtil.error(e.getMessage());
        }
    }
}
