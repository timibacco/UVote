package votingme.core.service;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.domain.Pageable;
import votingme.core.dto.LoginDTO;
import votingme.core.dto.UserDTO;

public interface UserService {
    void createOrganizer(UserDTO userDto, HttpServletRequest request) throws Exception;
    Object getAllUsers(Pageable pageable);
    Object getUserById(Long id) throws Exception;
    String updateUser(Long id, UserDTO userDto) throws Exception;
    String deleteUser(Long id) throws Exception;
    String login(LoginDTO loginDTO) throws Exception;

}