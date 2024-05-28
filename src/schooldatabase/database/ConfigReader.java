package schooldatabase.database;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class ConfigReader {
    private String url;
    private String username;
    private String password;

    public ConfigReader(String configFilePath) {
        try {
            File configFile = new File(configFilePath);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(configFile);
            doc.getDocumentElement().normalize();

            // Get the database configuration values
            Element dbElement = (Element) doc.getElementsByTagName("database").item(0);
            this.url = dbElement.getElementsByTagName("url").item(0).getTextContent();
            this.username = dbElement.getElementsByTagName("username").item(0).getTextContent();
            this.password = dbElement.getElementsByTagName("password").item(0).getTextContent();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getUrl() {
        return url;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
