package votingme.core.entity;

import jakarta.persistence.*;


import lombok.Data;
import lombok.RequiredArgsConstructor;

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

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Election election;

    private boolean has_voted;


    private boolean isActive;
    public boolean hasVoted() {
    	return has_voted;
    }




}
