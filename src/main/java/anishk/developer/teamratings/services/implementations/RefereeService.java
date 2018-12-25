package anishk.developer.teamratings.services.implementations;

import anishk.developer.teamratings.dto.RefereesOutput;
import anishk.developer.teamratings.models.Match;
import anishk.developer.teamratings.models.Referee;
import anishk.developer.teamratings.repositories.MatchesRepository;
import anishk.developer.teamratings.repositories.RefereesRepository;
import anishk.developer.teamratings.services.interfaces.IRefereeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("RefereeService")
@Slf4j
public class RefereeService implements IRefereeService {

    private MatchesRepository matchesRepository;
    private RefereesRepository refereesRepository;

    @Autowired
    public RefereeService(MatchesRepository matchesRepository, RefereesRepository refereesRepository) {
        this.matchesRepository = matchesRepository;
        this.refereesRepository = refereesRepository;
    }

    @Override
    public RefereesOutput getAllReferees() {
        List<Referee> referees = refereesRepository.findAll();
        return new RefereesOutput(referees);
    }

    @Override
    public Referee getRefereeByMatch(Long matchId) {
        Match match = matchesRepository.findByMatchId(matchId);
        return refereesRepository.findByRefereeId(match.getRefereeId());
    }

}
