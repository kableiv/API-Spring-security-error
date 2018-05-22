package files;

import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class ReusableMethods {



    public static XmlPath rawToXML(Response res){

        String responseString = res.asString();
        XmlPath x = new XmlPath(responseString);

        return x;
    }


    public static JsonPath rawToJson(Response res){

        String responseString = res.asString();
        JsonPath js = new JsonPath(responseString);

        return js;
    }

    public static String expires(JsonPath js, int i){
        ArrayList<String> expireArray = new ArrayList<String>();
        String expiresSource = js.getString("expires["+i+"]");
        expireArray.add(expiresSource);

        for (String dateStr : expireArray){
            if (dateStr != null){

                LocalDateTime parse = LocalDateTime.parse(expiresSource, DateTimeFormatter.ISO_DATE_TIME);
                String expiresFinal = parse.format(DateTimeFormatter.ofPattern("dd MM yyyy hha"));

                return expiresFinal;
            }
        }
        return "No avaliable date";

    }


    public static void insertData(int domainIdd,  String domainNamee, String statuss, String expiress, String autoRenew, String createdAtt, String domainProviderr){

        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.update("INSERT INTO domain(domainId, domainName, status, expires, renewAuto, createdAt, domainProvider)  VALUES (?,?,?,?,?,?,?)", domainIdd, domainNamee, statuss, expiress, autoRenew, createdAtt, domainProviderr );
    }

}
