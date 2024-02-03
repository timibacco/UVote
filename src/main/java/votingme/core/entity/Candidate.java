package votingme.core.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
@Entity
@Table(name = "candidates")
public class Candidate {
    @Id
    @GeneratedValue
    private Long id;


    @PrimaryKeyJoinColumn(referencedColumnName = "id")
    private User user;

    @ManyToMany
    private List<Election> election;

}
