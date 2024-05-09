package votingme.core.entity;

import jakarta.persistence.*;
import lombok.*;
import votingme.core.enums.UserType;

import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private UserType name;


    @ManyToMany(mappedBy = "roles", cascade = CascadeType.ALL)
    private List<Participant> participants;

}
