package GMIBankAPIObjectMapper.gmiUtil;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigReader {
    static Properties properties;
    static{
        String path = "configuration.properties";
        try{
            FileInputStream file = new FileInputStream(path);
            properties = new Properties();
            properties.load(file);
        }catch (Exception e){

        }
    }
    public static String getProperty(String key){
        return properties.getProperty(key);
    }
}
