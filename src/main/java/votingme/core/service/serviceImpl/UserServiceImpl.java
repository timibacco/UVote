package votingme.core.service.serviceImpl;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import votingme.core.dto.LoginDTO;
import votingme.core.dto.UserDTO;
import votingme.core.entity.Participant;
import votingme.core.entity.Role;
import votingme.core.enums.UserType;
import votingme.core.repository.ParticipantRepository;
import votingme.core.service.UserService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {


    private final ParticipantRepository participantrepo;

    private final PasswordEncoder encoder = new BCryptPasswordEncoder();




    @Override
    public void createOrganizer(UserDTO userDto,HttpServletRequest request) throws Exception {

        var existingUser = participantrepo.findByEmail(userDto.getEmail());
        if(existingUser.isPresent()) {
            throw new Exception("Participant already exists");
        }
        Role role = new Role();
        Participant participant = new Participant();
        participant.setEmail(userDto.getEmail());


        participant.setFirstname(userDto.getFirstName());
        participant.setLastname(userDto.getLastName());
        participant.setPassword(encoder.encode(userDto.getPassword()));

        role.setName(UserType.ORGANIZER);
        role.setParticipants(List.of(participant));

        participant.setRoles(List.of(role));
        participantrepo.saveAndFlush(participant);

    }



    

    @Override
    public  Object getAllParticipants(Pageable pageable) {
        var users =   participantrepo.findAll();


        return PageableExecutionUtils.getPage(users, pageable, users::size);

    }

    @Override
    public Object getParticipantById(Long id) throws Exception {
        return participantrepo.findById(id);
    }

    @Override
    public String updateParticipant(Long id, UserDTO userDto) throws Exception {
        return null;
    }

    @Override
    public String deleteParticipant(Long id) throws Exception {
        return null;
    }

    @Override
    public String login(LoginDTO loginDTO) throws Exception {
        return null;
    }
}