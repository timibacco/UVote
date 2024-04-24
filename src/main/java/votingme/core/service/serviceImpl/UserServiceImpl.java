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
import votingme.core.entity.Role;
import votingme.core.entity.User;
import votingme.core.enums.UserType;
import votingme.core.repository.UserRepository;
import votingme.core.service.UserService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;



    private final PasswordEncoder encoder = new BCryptPasswordEncoder();




    @Override
    public void createOrganizer(UserDTO userDto,HttpServletRequest request) throws Exception {

        var existingUser = userRepository.findByEmail(userDto.getEmail());
        if(existingUser.isPresent()) {
            throw new Exception("User already exists");
        }
        Role role = new Role();
        User user = new User();
        user.setEmail(userDto.getEmail());

        String[] fullName = userDto.getFullname().split(" ");
        if (fullName.length >= 2) {
            user.setFirstname(fullName[0]);
            user.setLastname(fullName[1]);
        }
        user.setFirstname(userDto.getFullname());
        user.setPassword(encoder.encode(userDto.getPassword()));

        role.setName(UserType.ORGANIZER);
        role.setUsers(List.of(user));

        user.setRoles(List.of(role));
        userRepository.saveAndFlush(user);

    }

    //TODO: implement pagination\\

    

    @Override
    public  Object getAllUsers(Pageable pageable) {
        var users =   userRepository.findAll();


        return PageableExecutionUtils.getPage(users, pageable, users::size);

    }

    @Override
    public Object getUserById(Long id) throws Exception {
        return userRepository.findById(id);
    }

    @Override
    public String updateUser(Long id, UserDTO userDto) throws Exception {
        return null;
    }

    @Override
    public String deleteUser(Long id) throws Exception {
        return null;
    }

    @Override
    public String login(LoginDTO loginDTO) throws Exception {
        return null;
    }
}