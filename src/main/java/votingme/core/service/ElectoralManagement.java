package votingme.core.service;

public interface ElectoralManagement {


    void endElection(Long electionID);

    void startElection(Long electionID);

    void authorizeVote(Long electionID, Long participantID);

    void revokeVote(Long electionID, Long participantID);

    void addOrganizer(Long electionID, Long organizerID);

    void removeOrganizer(Long electionID, Long organizerID);

    void deleteParticipant(Long electionID, Long participantID);

    void addParticipant(Long electionID, Long participantID);
}
