package APIUtilities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class APIPayload extends GenericUtils {

    public Map<String, Object> getAddPlacePayload(String testDataSheet, String testDataKey) {

        Map<String, String> testData;
        try {
            testData = getDataByKey(testDataSheet, testDataKey);
        } catch (Exception e) {
            throw new RuntimeException("Failed to retrieve test data for sheet: " + testDataSheet + ", key: " + testDataKey, e);
        }

        Map<String, Object> body = new HashMap<>();
        Map<String, Object> location = new HashMap<>();
        location.put("lat", Double.parseDouble(testData.get("lat")));
        location.put("lng", Double.parseDouble(testData.get("lng")));
        body.put("location", location);
        body.put("accuracy", Integer.parseInt(testData.get("accuracy")));
        body.put("name", testData.get("name"));
        body.put("phone_number", testData.get("phone_number"));
        body.put("address", testData.get("address"));
        String typesArr[] = testData.get("types").split(",");
        List<String> types = new ArrayList<>();
        for(String type : typesArr) {
            types.add(type.trim());
        }
        body.put("types", types);
        body.put("website", testData.get("website"));
        body.put("language", testData.get("language"));
        return body;
    }

}
