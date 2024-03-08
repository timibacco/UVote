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


    @ManyToMany( fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Organizer> organizers;

    @ManyToMany(mappedBy = "election")
     private List<Voter> voters;

    @ManyToMany( cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Candidate> candidates;

    private boolean isActive;


    private boolean isPublic;

    public boolean isActive() {
    	return this.isActive;
    }
}
