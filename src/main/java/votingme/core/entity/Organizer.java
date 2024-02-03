package votingme.core.entity;


import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@Entity
@RequiredArgsConstructor
@Table(name = "organizers")
public class Organizer {

    @Id
    @GeneratedValue
    private Long id;

    @PrimaryKeyJoinColumn
    private User user;

    @ManyToMany
    @JoinColumn(name = "election_id", nullable = false)
    private List<Election> election;

}
