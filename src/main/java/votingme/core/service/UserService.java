package votingme.core.service;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import votingme.core.dto.LoginDTO;
import votingme.core.dto.UserDTO;
import votingme.core.dto.UserResponseDTO;

public interface UserService {
    void createOrganizer(UserDTO userDto, HttpServletRequest request) throws Exception;
    Page<UserResponseDTO> getAllUsers(Pageable pageable);
    UserResponseDTO getUserById(Long id) throws Exception;
    String updateUser(Long id, UserDTO userDto) throws Exception;
    String deleteUser(Long id) throws Exception;
    String login(LoginDTO loginDTO) throws Exception;

}