package votingme.core.dto;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor

public class UserDTO {
    private String fullname;
    private String email;

    private String password;

}
