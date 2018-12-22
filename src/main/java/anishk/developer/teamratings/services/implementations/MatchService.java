package anishk.developer.teamratings.services.implementations;

import anishk.developer.teamratings.assembler.Assembler;
import anishk.developer.teamratings.dto.MatchesOutput;
import anishk.developer.teamratings.models.Match;
import anishk.developer.teamratings.repositories.LeaguesRepository;
import anishk.developer.teamratings.repositories.MatchesRepository;
import anishk.developer.teamratings.repositories.SeasonsRepository;
import anishk.developer.teamratings.repositories.TeamsRepository;
import anishk.developer.teamratings.services.interfaces.IMatchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("MatchService")
public class MatchService implements IMatchService {

    private static Logger logger = LoggerFactory.getLogger(MatchService.class);

    private Assembler assembler;
    private TeamsRepository teamsRepository;
    private MatchesRepository matchesRepository;
    private LeaguesRepository leaguesRepository;
    private SeasonsRepository seasonsRepository;

    @Autowired
    public MatchService(Assembler assembler, TeamsRepository teamsRepository, MatchesRepository matchesRepository,
                        LeaguesRepository leaguesRepository, SeasonsRepository seasonsRepository) {
        this.assembler = assembler;
        this.teamsRepository = teamsRepository;
        this.matchesRepository = matchesRepository;
        this.leaguesRepository = leaguesRepository;
        this.seasonsRepository = seasonsRepository;
    }

    @Override
    public MatchesOutput getAllMatchesByTeamBetweenDates(Integer teamId, Date startDate, Date endDate) {
        List<Match> matches = matchesRepository.findAllByTeamIdAndFixtureDateBetweenOrderByFixtureDateAsc(teamId, startDate, endDate);
        return new MatchesOutput(matches);
    }

    @Override
    public MatchesOutput getAllMatchesByTeamLeagueAndSeason(Integer teamId, Integer leagueId, Integer seasonId) {
        List<Match> matches = matchesRepository.findAllByTeamIdAndLeagueIdAndSeasonId(teamId, leagueId, seasonId);
        return new MatchesOutput(matches);
    }

}
