package fablemedia.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.text.ParseException;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) throws IOException, ParseException {
        SpringApplication.run(DemoApplication.class, args);
        GoDaddyGET gett = new GoDaddyGET();
        gett.getData();
        gett.Test();
    }
}
