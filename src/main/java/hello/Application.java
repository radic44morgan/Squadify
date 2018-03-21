package hello;

import java.io.File;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
//        SpotifyController controller = new SpotifyController();
//        File authhtml = new File("main/resources/templates/auth.html");
        //write authgen to auth.html
    }
    
    
}
