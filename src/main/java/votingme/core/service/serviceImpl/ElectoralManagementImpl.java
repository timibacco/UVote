package votingme.core.service.serviceImpl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import votingme.core.repository.ElectionRepository;
import votingme.core.repository.OrganizerRepository;
import votingme.core.repository.ParticipantRepository;
import votingme.core.service.ElectoralManagement;

import java.time.LocalDateTime;
import java.util.List;


@Service
@RequiredArgsConstructor
public class ElectoralManagementImpl implements ElectoralManagement{


    private final ElectionRepository electionRepository;

    private final ParticipantRepository participantRepository;

    private final OrganizerRepository organizerRepository;
    public void endElection(Long electionID) {

        /*
         * 1. Find the election
         * 2. Set the election to inactive
         * 3. Save the election
         **/


        var election = electionRepository.findById(electionID)
                .orElseThrow( () -> new RuntimeException("Election not found") );


        election.setActive(false);
        electionRepository.saveAndFlush(election);
    }

    public void startElection(Long electionID) {

        /*
         * 1. Find the election
         * 2. Set the election to active
         * 3. Set the start date to now
         * 4. Save the election
         * */

        var election = electionRepository.findById(electionID)
                .orElseThrow( () -> new RuntimeException("Election not found") );

        election.setActive(true);
        election.setStart_date(LocalDateTime.now());
        electionRepository.saveAndFlush(election);

    }




    // TODO:This method is not implemented correctly. It should be implemented as follows:



    public void authorizeVote(Long electionID, Long participantID) {

        /**
         * Authorize a participant to vote in an election
         *   There would be no need to be sure if participant
         *      is a Voter type and not a Candidate type.
         *        This is because the participant would have been
         *            verified before being added to the election.
         * @param electionID
         * @param participantID
         **/

        var election = electionRepository.findById(electionID)
                .orElseThrow( () -> new RuntimeException("Election not found") );

        var participant = participantRepository.findById(participantID)
                .orElseThrow( () -> new RuntimeException("Participant not found") );


        if (participant.getVoterAccount() == null) {                         /* If, You don't have a voter's account */
            throw new RuntimeException("Participant is not a voter");
        }


        if (!election.getVoters().contains(participant.getVoterAccount())) {       /* And, You are not part of this election */
            throw new RuntimeException("Participant is not part of the election");

        }
                                                                                          /* Why should we authorize you then? */

        participant.getVoterAccount().setCanVote(true);
        participantRepository.saveAndFlush(participant);


        electionRepository.saveAndFlush(election);

    }

    public void revokeVote(Long electionID, Long participantID) {

        /**
        * Revoke a participant's right to vote in an election
        * @param electionID
        * @param participantID
        **/

        var election = electionRepository.findById(electionID)
                .orElseThrow( () -> new RuntimeException("Election not found") );

        var participant = participantRepository.findById(participantID)
                .orElseThrow( () -> new RuntimeException("Participant not found") );

        if (participant.getVoterAccount() == null) {     /* If, You don't have a voter's account */

            throw new RuntimeException("Participant is not a voter");
        }

        participant.getVoterAccount().setCanVote(false);
        participant.getVoterAccount().setHasVoted(false);

        participantRepository.saveAndFlush(participant);
    }

    public void addOrganizer(Long electionID, Long organizerID) {

    }

    public void removeOrganizer(Long electionID, Long organizerID) {

    }

    public void deleteParticipant(Long electionID, Long participantID) {

    }

    public void addParticipant(Long electionID, Long participantID) {

    }

    public Object getVoters(Long electionID) {
        var election = electionRepository.findById(electionID)
                .orElseThrow( () -> new RuntimeException("Election not found") );
        return election.getVoters();
    }

    public Object getMyElections(Long OrganizerID) {
        return electionRepository.findByOrganizers(
                List.of(organizerRepository.findById(OrganizerID)
                        .orElseThrow(
                                () -> new RuntimeException("Organizer not found")
                        )
                ));
    }
}
