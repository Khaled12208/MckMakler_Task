package org.herokuapp.helpers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import static org.testng.AssertJUnit.assertEquals;

public class Helpers {
    public URL getURL(String fileName) {
        return getClass().getClassLoader().getResource(fileName);
    }


    public Logger log() {
        return LogManager.getLogger(Thread.currentThread().getStackTrace()[2].getClassName());
    }

    public String getAlphaNumericString(int n) {
        {

            // lower limit for LowerCase Letters
            int lowerLimit = 97;

            // lower limit for LowerCase Letters
            int upperLimit = 122;

            Random random = new Random();

            // Create a StringBuffer to store the result
            StringBuffer r = new StringBuffer(n);

            for (int i = 0; i < n; i++) {

                // take a random value between 97 and 122
                int nextRandomChar = lowerLimit
                        + (int)(random.nextFloat()
                        * (upperLimit - lowerLimit + 1));

                // append a character at the end of bs
                r.append((char)nextRandomChar);
            }

            // return the resultant string
            return r.toString();
        }
    }

    // This Function to read files as String
    public String readResourceFileAsString(String file, Class<?> Class) throws Exception {
        String TestResourceFilePath = BuildPath(Class, file);
        return new String(Files.readAllBytes(Paths.get(TestResourceFilePath)));
    }

    public Map<String, Object> readResourceFileAsMap(String file, Class<?> Class) throws IOException, JSONException {
        String TestResourceFilePath = BuildPath(Class, file);
        JSONObject jsonObject=null;
        try {
            jsonObject= new JSONObject(new String(Files.readAllBytes(Paths.get(TestResourceFilePath))));

        }catch (JSONException err){
        }

        return jsonToMap(jsonObject);
    }

    // This Function to Build the File Path based on the class path
    private String BuildPath(Class<?> Class, String TestResourceFile) {

        String ClassName = Class.getName();
        String[] Path = ClassName.split("\\.");
        StringBuffer sb = new StringBuffer();
        sb.append("src/test/resources/");
        for (int i = 0; i < Path.length - 1; i++) {
            sb.append(Path[i] + "/");
        }
        sb.append(TestResourceFile);
        return sb.toString();

    }

    public  Map<String, Object> jsonToMap(JSONObject json) throws JSONException {
        Map<String, Object> retMap = new HashMap<String, Object>();

        if(json != JSONObject.NULL) {
            retMap = toMap(json);
        }
        return retMap;
    }

    public static Map<String, Object> toMap(JSONObject object) throws JSONException {
        Map<String, Object> map = new HashMap<String, Object>();

        Iterator<String> keysItr = object.keys();
        while(keysItr.hasNext()) {
            String key = keysItr.next();
            Object value = object.get(key);

            if(value instanceof JSONArray) {
                value = toList((JSONArray) value);
            }

            else if(value instanceof JSONObject) {
                value = toMap((JSONObject) value);
            }
            map.put(key, value);
        }
        return map;
    }

    public static List<Object> toList(JSONArray array) throws JSONException {
        List<Object> list = new ArrayList<Object>();
        for (int i = 0; i < array.length(); i++) {
            Object value = array.get(i);
            if (value instanceof JSONArray) {
                value = toList((JSONArray) value);
            } else if (value instanceof JSONObject) {
                value = toMap((JSONObject) value);
            }
            list.add(value);
        }
        return list;
    }


}
