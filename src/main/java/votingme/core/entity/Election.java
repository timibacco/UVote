package votingme.core.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;


@Data
@Entity
@RequiredArgsConstructor
@Table(name = "elections")
public class Election {
    @Id
    @GeneratedValue
    private Long id;


    @Column(name = "name", nullable = false, unique = true)
    private String name;

    private String description;

    @Column(name = "start_date", nullable = false)
    private LocalDateTime start_date;


    @Column(name = "end_date", nullable = false)
    private LocalDateTime end_date;


    @ManyToMany
    private List<Organizer> organizers;

    @ManyToMany(mappedBy = "election")
     private List<Voter> voters;

    @ManyToMany(mappedBy = "election")
    private List<Candidate> candidates;

    private Boolean isActive;


    private Boolean isPublic;

    public Boolean isActive() {
    	return this.isActive;
    }
}
