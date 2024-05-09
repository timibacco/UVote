package votingme.core.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import votingme.core.repository.ParticipantRepository;

import java.util.regex.Pattern;

public class Request {

    @Autowired
    private ParticipantRepository participantRepository;



    @Autowired
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();




    public String encodePassword(String password){
        return passwordEncoder.encode(password);
    }

    public boolean isPasswordMatch(String password, String encodedPassword){
        return passwordEncoder.matches(password, encodedPassword);
    }

    public boolean isEmailValid(String email) {
        Pattern pattern = Pattern.compile("^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$");

        return email != null && pattern.matcher(email).matches();
    }

    public <T,V>  V map(T from, V to){
        TypeReference<V> type = new TypeReference<>() {};

        ObjectMapper mapper = new ObjectMapper();

        return mapper.convertValue(from, type);




    }
}