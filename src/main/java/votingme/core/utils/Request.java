package votingme.core.utils;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import votingme.core.config.jwt.JwtService;
import votingme.core.entity.User;
import votingme.core.repository.UserRepository;

public class Request {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    public User getLoggedInUser(HttpServletRequest request){
        String jwt = request.getHeader("Authorization").substring(7);
        String username = jwtService.extractUsername(jwt);
        return userRepository.findByEmail(username).orElseThrow(() -> new
                UsernameNotFoundException("User does not exist"));

    }

    public String encodePassword(String password){
        return passwordEncoder.encode(password);
    }

    public boolean isPasswordValid(String password, String encodedPassword){
        return passwordEncoder.matches(password, encodedPassword);
    }


}
