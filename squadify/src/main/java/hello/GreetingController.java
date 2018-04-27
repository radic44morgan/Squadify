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
public class GreetingController
{

    private QueueController controller = new QueueController();
    private ArrayList<UserModel> users = new ArrayList<UserModel>();
    private YoutubeController yc = new YoutubeController();

    @GetMapping("/login")
    public String loginForm(Model model)
    {
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
            model.addAttribute("songmodel", new SongModel());
            Map<String, SongModel> songs = new LinkedHashMap<String, SongModel>();
            songs.put("Vizine", new SongModel("Vizine Lil Wayne", yc.findURL("Vizine Lil Wayne")));
            model.addAttribute("queuename", controller.getQueues().get(temp.getQueuecode()).getName());
            model.addAttribute("queuecode", temp.getQueuecode());
            model.addAttribute("list", controller.getQueues().get(temp.getQueuecode()).getSongs());
            model.addAttribute("songs", songs);
            model.addAttribute("url", controller.getQueues().get(temp.getQueuecode()).getUrl());
            return "queuestatus";
        } else
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
        queuemodel.setPlaylistID(yc.createPlaylist(queuemodel.getName()));
        controller.addQueue(queuemodel);
        model.addAttribute("code", queuemodel.getCode());
        model.addAttribute("name", queuemodel.getName());
        return "queue_created";
    }

    @PostMapping("/addsong/{code}")
    public String songSubmit(@PathVariable(value = "code") String code, @ModelAttribute SongModel songmodel, Model model)
    {
        songmodel.setUrl(yc.findID(songmodel.getName()).get(0));
        songmodel.setName(yc.findID(songmodel.getName()).get(1));
        controller.getQueues().get(code).addSong(yc, songmodel);
        model.addAttribute("queuename", controller.getQueues().get(code).getName());
        model.addAttribute("queuecode", code);
        model.addAttribute("list", controller.getQueues().get(code).getSongs());
        model.addAttribute("url", controller.getQueues().get(code).getUrl());
        return "queuestatus";
    }
    
    @GetMapping("/queue/{code}")
    public String queueStatus(Model model, @PathVariable(value = "code") String code)
    {
        model.addAttribute("queuename", controller.getQueues().get(code).getName());
        model.addAttribute("queuecode", code);
        model.addAttribute("list", controller.getQueues().get(code).getSongs());
        model.addAttribute("url", controller.getQueues().get(code).getUrl());
        return "queuestatus";
    }


    @ModelAttribute("songmodel")
    public SongModel createModel()
    {
        return new SongModel();
    }

}
