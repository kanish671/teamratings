package anishk.developer.teamratings.services.implementations;

import anishk.developer.teamratings.assembler.Assembler;
import anishk.developer.teamratings.dto.*;
import anishk.developer.teamratings.models.*;
import anishk.developer.teamratings.repositories.*;
import anishk.developer.teamratings.services.interfaces.IMatchRatingsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("MatchRatingsService")
public class MatchRatingsService implements IMatchRatingsService {

    private static Logger logger = LoggerFactory.getLogger(MatchRatingsService.class);

    private Assembler assembler;
    private MatchesRepository matchesRepository;
    private LeaguesRepository leaguesRepository;
    private SeasonsRepository seasonsRepository;
    private TeamsRepository teamsRepository;
    private ManagersRepository managersRepository;
    private PlayersRepository playersRepository;
    private RefereesRepository refereesRepository;

    private TeamRatingsService teamRatingsService;
    private ManagerRatingsService managerRatingsService;
    private PlayerRatingsService playerRatingsService;
    private RefereeRatingsService refereeRatingsService;

    @Autowired
    public MatchRatingsService(Assembler assembler, MatchesRepository matchesRepository,
                               LeaguesRepository leaguesRepository, SeasonsRepository seasonsRepository,
                               TeamsRepository teamsRepository, ManagersRepository managersRepository,
                               PlayersRepository playersRepository, RefereesRepository refereesRepository,
                               TeamRatingsService teamRatingsService, ManagerRatingsService managerRatingsService,
                               PlayerRatingsService playerRatingsService, RefereeRatingsService refereeRatingsService) {
        this.assembler = assembler;
        this.matchesRepository = matchesRepository;
        this.leaguesRepository = leaguesRepository;
        this.seasonsRepository = seasonsRepository;
        this.teamsRepository = teamsRepository;
        this.managersRepository = managersRepository;
        this.playersRepository = playersRepository;
        this.refereesRepository = refereesRepository;
        this.teamRatingsService = teamRatingsService;
        this.managerRatingsService = managerRatingsService;
        this.playerRatingsService = playerRatingsService;
        this.refereeRatingsService = refereeRatingsService;
    }

    @Override
    @Transactional
    public void saveMatchRating(MatchRatingRequestInput matchRatingRequestInput) {
        logger.info("Saving rating for matchId: {}", matchRatingRequestInput.getMatchId());

        teamRatingsService.saveTeamRating(matchRatingRequestInput.getTeamRating());
        managerRatingsService.saveManagerRating(matchRatingRequestInput.getManagerRating());
        refereeRatingsService.saveRefereeRating(matchRatingRequestInput.getRefereeRating());
        for(PlayerRatingRequestInput playerRating : matchRatingRequestInput.getPlayerRatings()) {
            playerRatingsService.savePlayerRating(playerRating);
        }
    }

    @Override
    public MatchRatingsOutput getMatchRatings(Long matchId) {
        logger.info("Getting Match rating information for matchId: {}", matchId);

        Match match = matchesRepository.findByMatchId(matchId);

        if(match != null) {
            Team team = teamsRepository.findByTeamId(match.getTeamId());
            League league = leaguesRepository.findByLeagueId(match.getLeagueId());
            Season season = seasonsRepository.findBySeasonId(match.getSeasonId());
            Manager manager = managersRepository.findByTeamId(team.getTeamId());
            Referee referee = refereesRepository.findByRefereeId(match.getRefereeId());
            List<Player> players = playersRepository.findAllByTeamId(team.getTeamId());

            MatchRatingManagerOutput managerRating =
                    new MatchRatingManagerOutput(manager,
                            managerRatingsService.getManagerRatingByMatch(manager.getManagerId(), matchId).getRating());
            MatchRatingTeamOutput teamRating =
                    new MatchRatingTeamOutput(team,
                            teamRatingsService.getTeamRatingByMatch(team.getTeamId(), matchId).getRating());
            MatchRatingRefereeOutput refereeRating =
                    new MatchRatingRefereeOutput(referee,
                            refereeRatingsService.getRefereeRatingByMatch(referee.getRefereeId(), matchId).getRating());
            List<MatchRatingPlayerOutput> playerRatings = new ArrayList<>();

            for (Player player: players) {
                playerRatings.add(new MatchRatingPlayerOutput(player,
                        playerRatingsService.getPlayerRatingByMatch(player.getPlayerId(), matchId).getRating()));
            }

            return assembler.populateMatchRatingsOutput(match, league, season, teamRating, managerRating,
                    refereeRating, playerRatings);
        } else {
            throw new IllegalArgumentException("matchId doesn't match existing data");
        }
    }
}
