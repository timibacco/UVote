package votingme.core.entity;


import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

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

    @ManyToOne
    @JoinColumn(name = "election_id", nullable = false)
    private Election election;

}
