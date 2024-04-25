package votingme.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface ParticipantRepository extends JpaRepository<votingme.core.entity.Participant, Long> {

    Optional<votingme.core.entity.Participant> findByEmail(String email);

}
