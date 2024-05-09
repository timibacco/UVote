package votingme.core.controller;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import votingme.core.dto.UserDTO;
import votingme.core.service.UserService;


@Slf4j
@Controller
@RequiredArgsConstructor
public class Auth {

    @Autowired
    private final UserService userService;


    @GetMapping("/")
    public String index() {

        return "index";


    }

    @GetMapping("/register")
    public String register(Model model) {
        UserDTO user = new UserDTO();
        model.addAttribute("user", user);
        return "register";

    }

    @PostMapping("/register/save")
    public String register(@Valid @ModelAttribute("user") UserDTO userDto,
                           Model model,
                           BindingResult result,
                           HttpServletRequest request) throws Exception {
            try {
                userService.createOrganizer(userDto, request);
                return "redirect:/register?success";
            } catch(Exception e) {
                log.info("Error while posting a new user {}",e.getMessage());
                return "redirect:/register?error";
            }


    }

//    @GetMapping("/dashboard")
//    public String dashboard(Model model) {
//
//        return "dashboard";
//
//    }

    @GetMapping("/login")
    public String login(Model model) {

        return "login";

    }
}
