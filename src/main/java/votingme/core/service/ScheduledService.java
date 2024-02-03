package votingme.core.service;

import org.springframework.scheduling.annotation.Scheduled;
public class ScheduledService {

    public void scheduleElection() {



    }

    public void scheduleElectionReminder() {



    }


    @Scheduled(cron = "0 0 0 * * *")
    public void scheduleElectionResult() {





    }

    public void scheduleElectionResultReminder() {



    }

    public void eraseInactiveElections(){

    }
}
