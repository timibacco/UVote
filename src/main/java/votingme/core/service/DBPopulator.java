package votingme.core.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import votingme.core.entity.User;
import votingme.core.repository.UserRepository;
import votingme.core.utils.Request;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class DBPopulator {

    private final UserRepository userRepository;
    private final Request request;

    @PostConstruct
    public void populate() {
        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<List<User>> typeReference = new TypeReference<>() {
        };

        ClassPathResource resource = new ClassPathResource("users.json");
        try (InputStream inputStream = resource.getInputStream()) {
            List<User> users = objectMapper.readValue(inputStream, typeReference);
            for (User user : users) {
                user.setPassword(request.encodePassword(user.getPassword()));
                log.info("\n\nUsers saved in to database");
                userRepository.save(user);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
