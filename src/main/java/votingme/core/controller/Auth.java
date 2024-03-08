package votingme.core.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class Auth {


    @GetMapping("/")
    public String index(Model model) {

        return "index";


    }

    @GetMapping("/register")
    public String register(Model model) {

        return "register";

    }
    @GetMapping("/dashboard")
    public String dashboard(Model model) {

        return "dashboard";

    }
}
