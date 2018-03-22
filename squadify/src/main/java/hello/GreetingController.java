package hello;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class GreetingController {

    
    @GetMapping("/u")
    public String userForm(Model model) {
        model.addAttribute("user", new UserModel());
        return "user";
    }


}