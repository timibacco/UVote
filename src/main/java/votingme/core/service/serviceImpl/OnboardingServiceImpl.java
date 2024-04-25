package votingme.core.service.serviceImpl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import votingme.core.service.OnboardingService;

@Service
@RequiredArgsConstructor
public class OnboardingServiceImpl implements OnboardingService {


    @Autowired



    @Override
    public void onboardParticipants(String participantID, String electionID,
                                   String verificationToken,
                                    Object participantInfo) {








    }

    @Override
    public void onboardNewElectoralExercise() {

    }
}
