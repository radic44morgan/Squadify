package hello;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class GreetingController {

    private QueueController controller = new QueueController();
    private ArrayList<UserModel> users = new ArrayList<UserModel>();
    private SpotifyController sc = new SpotifyController();
    
    @GetMapping("/login")
    public String loginForm(Model model) {
        model.addAttribute("usermodel", new UserModel());
        return "login";
    }
    
    @PostMapping("/login")
    public String loginSubmit(@ModelAttribute UserModel usermodel, Model model)
    {
        model.addAttribute("username", usermodel.getUsername());
        UserModel temp = new UserModel();
        temp.setUsername(usermodel.getUsername());
        temp.setQueuecode(usermodel.getQueuecode());
        if (controller.addUserToQueue(temp, usermodel.getQueuecode()))
        {
            model.addAttribute("queuename", controller.getQueues().get(temp.getQueuecode()).getName());
            model.addAttribute("size", controller.getQueues().get(temp.getQueuecode()).getUserSize());
            return "success";
        }
        else
        {
            return "failure";
        }
    }
    
    @GetMapping("/create")
    public String createForm(Model model) 
    {
        model.addAttribute("queuemodel", new QueueModel());
        return "create";
    }
    
    @PostMapping("/create")
    public String createSubmit(@ModelAttribute QueueModel queuemodel, Model model)
    {
        System.out.println("HERE: " + queuemodel.getName()); // name is NULL
        controller.addQueue(queuemodel);
        model.addAttribute("code", queuemodel.getCode());
        model.addAttribute("name", queuemodel.getName());
        return "queue_created";
    }

    @RequestMapping("/spotify_login")
    public String spotifyLogin(Model model)
    {
        File spotifyLoginFile = new File("src/main/resources/templates/spotify_login.html");
        try
        {
            FileWriter writer = new FileWriter(spotifyLoginFile);
            BufferedWriter bw = new BufferedWriter(writer);
            bw.write(sc.generateAuthView());
            bw.close();
        }
        catch(IOException ex)
        {
            System.out.println("HEY ERROR: " + ex.getMessage());
        }
        return "spotify_login";
    }
    
    @RequestMapping("/en/login")
    public String returnLogin()
    {
        return "spotify_login";
    }

}