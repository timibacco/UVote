package votingme.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import votingme.core.entity.Organizer;

@Repository
public interface OrganizerRepository extends JpaRepository<Organizer, Long>{


}
