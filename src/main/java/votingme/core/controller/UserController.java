package votingme.core.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import votingme.core.service.UserService;

import java.util.Optional;


@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class UserController {


    private final UserService userService;
    
    @GetMapping("/participants")
    public String getParticipants(@RequestParam("page") Optional<Integer> page, Model model) {
        int currentPage = page.orElse(1);
        PageRequest pageRequest = PageRequest.of(currentPage - 1, 10);
        Object userPage = userService.getAllParticipants(pageRequest);

        model.addAttribute("userPage", userPage);
        model.addAttribute("currentPage", currentPage);
        return "users";
    }
}
