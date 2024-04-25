package votingme.core.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.core.io.ClassPathResource;
import votingme.core.repository.ParticipantRepository;
import votingme.core.utils.Request;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class DBPopulator {

    private final ParticipantRepository participantRepository;
    private final Request request;

    @PostConstruct
    public void populate() {
        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<List<votingme.core.entity.Participant>> typeReference = new TypeReference<>() {
        };


            ClassPathResource resource = new ClassPathResource("users.json");

        try (InputStream inputStream = resource.getInputStream()) {
            List<votingme.core.entity.Participant> participants = objectMapper.readValue(inputStream, typeReference);
            for (votingme.core.entity.Participant participant : participants) {
                participant.setPassword(request.encodePassword(participant.getPassword()));
                log.info("\n\nUsers saved in to database");
                this.participantRepository.save(participant);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    }

