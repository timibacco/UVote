package votingme.core.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;


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

    private Set<Organizer> organizers;

    private Set<Voter> voters;

    private Set<Candidate> candidates;

    private Boolean is_active;


    private Boolean is_public;

    public Boolean isActive() {
    	return is_active;
    }
}
