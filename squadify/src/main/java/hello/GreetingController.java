package hello;

import java.util.ArrayList;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class GreetingController {

    private QueueController controller = new QueueController();
    private ArrayList<UserModel> users = new ArrayList<UserModel>();
    
    @GetMapping("/login")
    public String loginForm(Model model) {
        model.addAttribute("usermodel", new UserModel());
        return "login";
    }
    
    @PostMapping("/login")
    public String loginSubmit(@ModelAttribute UserModel usermodel, Model model)
    {
        model.addAttribute("username", usermodel.getUsername());
        if (controller.addUserToQueue(usermodel, usermodel.getQueuecode()))
        {
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
        controller.addQueue(queuemodel);
        model.addAttribute("code", queuemodel.getCode());
        model.addAttribute("name", queuemodel.getName());
        return "queue_created";
    }


}