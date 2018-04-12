package hello;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
            model.addAttribute("song", new SongModel());
            Map<String, SongModel> songs = new LinkedHashMap<String,SongModel>();
            songs.put("Vizine", new SongModel("Vizine","Lil Wayne"));
            model.addAttribute("queuename", controller.getQueues().get(temp.getQueuecode()).getName());
            model.addAttribute("queuecode", temp.getQueuecode());
            model.addAttribute("list",controller.getQueues().get(temp.getQueuecode()).getSongs());
            model.addAttribute("songs", songs);
            return "queuestatus";
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
        controller.addQueue(queuemodel);
        model.addAttribute("code", queuemodel.getCode());
        model.addAttribute("name", queuemodel.getName());
        return "queue_created";
    }
    
    @RequestMapping("/addsong/{code}")
    public String songSubmit(@PathVariable(value="code") String code ,@ModelAttribute SongModel songmodel, Model model)
    {
        controller.getQueues().get(code).addSong(new SongModel("Vizine","Lil Wayne"));
//        model.addAttribute("code", queuemodel.getCode());
//        model.addAttribute("name", queuemodel.getName());
        model.addAttribute("queuename", controller.getQueues().get(code).getName());
        model.addAttribute("queuecode", code);
        model.addAttribute("list",controller.getQueues().get(code).getSongs());
        return "queuestatus";
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