package votingme.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import votingme.core.entity.Election;
import votingme.core.entity.Organizer;

import java.util.List;
import java.util.Set;
@Repository
public interface ElectionRepository extends JpaRepository<Election, Long> {

    @Query("SELECT e FROM Election e WHERE e.is_active = true")
    List<Election> findAllByIsActiveTrue();

    List<Election> findAllByIsActiveTrueAndIsPublicTrue();

    List<Election> findByOrganizers(Set<Organizer> organizers);
    List<Election> findByOrganizer(Organizer organizer);
    List<Election> findAllByIsActiveAndPublic();
}






