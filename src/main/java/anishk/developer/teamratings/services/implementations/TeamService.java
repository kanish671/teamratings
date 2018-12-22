package anishk.developer.teamratings.services.implementations;

import anishk.developer.teamratings.assembler.Assembler;
import anishk.developer.teamratings.dto.TeamsOutput;
import anishk.developer.teamratings.models.Team;
import anishk.developer.teamratings.repositories.LeaguesRepository;
import anishk.developer.teamratings.repositories.MatchesRepository;
import anishk.developer.teamratings.repositories.SeasonsRepository;
import anishk.developer.teamratings.repositories.TeamsRepository;
import anishk.developer.teamratings.services.interfaces.ITeamService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("TeamService")
public class TeamService implements ITeamService {

    private static Logger logger = LoggerFactory.getLogger(TeamService.class);

    private Assembler assembler;
    private TeamsRepository teamsRepository;
    private LeaguesRepository leaguesRepository;
    private MatchesRepository matchesRepository;

    @Autowired
    public TeamService(Assembler assembler, TeamsRepository teamsRepository, LeaguesRepository leaguesRepository,
                       MatchesRepository matchesRepository) {
        this.assembler = assembler;
        this.teamsRepository = teamsRepository;
        this.leaguesRepository = leaguesRepository;
        this.matchesRepository = matchesRepository;
    }

    @Override
    public TeamsOutput getAllTeams() {
        return new TeamsOutput(teamsRepository.findAll());
    }

    @Override
    public TeamsOutput getAllTeamsByLeague(Integer leagueId) {
        List<Integer> teamIds = matchesRepository.findDistinctTeamsForLeague(leagueId);
        return new TeamsOutput(teamsRepository.findAllByTeamIdIn(teamIds));
    }
}
