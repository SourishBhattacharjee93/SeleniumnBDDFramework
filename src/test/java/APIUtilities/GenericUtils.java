package APIUtilities;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.RequestSpecification;

import java.io.File;
import java.io.FileInputStream;
import java.util.Map;
import java.util.Properties;

public class GenericUtils {

    public String getProperties(String key) {
        Properties prop = null;
        try {
            prop = new Properties();
            FileInputStream fis = new FileInputStream("src/test/resources/Properties/config.properties");
            prop.load(fis);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return prop.getProperty(key);
    }

    public Map<String, String> getDataByKey(String scenario, String key) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Map<String, String>> allData = mapper.readValue(
                new File("src/test/resources/TestData/" + scenario + ".json"),
                new TypeReference<>() {
                }
        );
        return allData.get(key);
    }

    public RequestSpecification googleAPIRequestSpec() throws Exception {


        RequestSpecification request = new RequestSpecBuilder()
                .setBaseUri(getProperties("baseUri"))
                .addHeader("Content-Type", "application/json")
                .addQueryParam("key", "qaclick123")
                .log(LogDetail.ALL)
                .build();

        return request;
    }


}
