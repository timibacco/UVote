package votingme.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import votingme.core.entity.Election;
import votingme.core.entity.Organizer;

import java.util.List;
@Repository
public interface ElectionRepository extends JpaRepository<Election, Long> {

    List<Election> findAllByIsActiveTrue();

    List<Election> findAllByIsActiveTrueAndIsPublicTrue();

    List<Election> findByOrganizers(Organizer organizers);
}






