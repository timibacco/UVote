package votingme.core.service;

public interface OnboardingService {


    void onboardParticipants(String participantID, String electionID, String verificationToken,
                             Object participantInfo
                                );// TODO: onboarding of participants

    void onboardNewElectoralExercise();




}
