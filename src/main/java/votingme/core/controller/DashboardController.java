package votingme.core.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticatedPrincipal;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import votingme.core.entity.Organizer;
import votingme.core.service.serviceImpl.ElectoralManagementImpl;

// DashboardController.java
@Controller
public class DashboardController {

    @Autowired
    private ElectoralManagementImpl electoralManagement;

    @GetMapping("/dashboard")
    public String dashboard() {
        return "dashboard";
    }

    @GetMapping("/myElections")
    public String myElections(Model model, AuthenticatedPrincipal principal) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        var user = (Organizer) authentication.getPrincipal();
       var  elections = electoralManagement.getMyElections(user.getId());
        model.addAttribute("elections", elections);
        return "myElections";
    }

//    @GetMapping("/electionDetails/{electionId}")
//    public String electionDetails(@PathVariable Long electionId, Model model) {
//        var election = electoralManagement.getElectionDetails(electionId);
//        model.addAttribute("election", election);
//        return "electionDetails";
//    }
}
