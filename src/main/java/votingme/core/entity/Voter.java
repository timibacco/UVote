package votingme.core.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@Entity
@RequiredArgsConstructor
@Table(name = "voters")
public class Voter {

    @Id
    @GeneratedValue
    private Long id;


    @OneToOne
    private Participant participant;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Election> election;

    private boolean hasVoted;


    private boolean isActive;


    private boolean canVote;




}
