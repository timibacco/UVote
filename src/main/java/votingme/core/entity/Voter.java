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


    @PrimaryKeyJoinColumn(referencedColumnName = "id")
    private User user;

    @ManyToOne
    private Election election;

    private Boolean has_voted;

    public Boolean hasVoted() {
    	return has_voted;
    }




}
