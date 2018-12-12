package anishk.developer.teamratings.services.implementations;

import anishk.developer.teamratings.assembler.Assembler;
import anishk.developer.teamratings.dto.LeaguesOutput;
import anishk.developer.teamratings.models.League;
import anishk.developer.teamratings.repositories.LeaguesRepository;
import anishk.developer.teamratings.repositories.MatchesRepository;
import anishk.developer.teamratings.repositories.SeasonsRepository;
import anishk.developer.teamratings.repositories.TeamsRepository;
import anishk.developer.teamratings.services.interfaces.ILeagueService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("LeagueService")
public class LeagueService implements ILeagueService {

    private static Logger logger = LoggerFactory.getLogger(LeagueService.class);

    private Assembler assembler;
    private TeamsRepository teamsRepository;
    private MatchesRepository matchesRepository;
    private LeaguesRepository leaguesRepository;
    private SeasonsRepository seasonsRepository;

    @Autowired
    public LeagueService(Assembler assembler, MatchesRepository matchesRepository, LeaguesRepository leaguesRepository) {
        this.assembler = assembler;
        this.matchesRepository = matchesRepository;
        this.leaguesRepository = leaguesRepository;
    }

    @Override
    public LeaguesOutput getAllLeaguesByTeam(Integer teamId) {
        List<Integer> leagueIds = matchesRepository.findDistinctLeaguesForTeam(teamId);
        List<League> leagues = new ArrayList<>();
        for(Integer leagueId : leagueIds) {
            leagues.add(leaguesRepository.findByLeagueId(leagueId));
        }
        return new LeaguesOutput(leagues);
    }
}
