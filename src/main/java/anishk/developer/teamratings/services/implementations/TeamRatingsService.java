package anishk.developer.teamratings.services.implementations;

import anishk.developer.teamratings.assembler.Assembler;
import anishk.developer.teamratings.dto.RatingByMatch;
import anishk.developer.teamratings.dto.TeamRatingByMatchOutput;
import anishk.developer.teamratings.dto.TeamRatingsBetweenDatesOutput;
import anishk.developer.teamratings.dto.TeamRatingRequestInput;
import anishk.developer.teamratings.models.*;
import anishk.developer.teamratings.repositories.*;
import anishk.developer.teamratings.services.interfaces.ITeamRatingsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service("TeamRatingsService")
public class TeamRatingsService implements ITeamRatingsService {

    private static Logger logger = LoggerFactory.getLogger(TeamRatingsService.class);

    private Assembler assembler;
    private TeamsRepository teamsRepository;
    private LeaguesRepository leaguesRepository;
    private MatchesRepository matchesRepository;
    private SeasonsRepository seasonsRepository;
    private TeamRatingsRepository teamRatingsRepository;

    @Autowired
    public TeamRatingsService(Assembler assembler, TeamsRepository teamsRepository,
                              LeaguesRepository leaguesRepository, MatchesRepository matchesRepository,
                              SeasonsRepository seasonsRepository, TeamRatingsRepository teamRatingsRepository) {
        this.assembler = assembler;
        this.teamsRepository = teamsRepository;
        this.leaguesRepository = leaguesRepository;
        this.matchesRepository = matchesRepository;
        this.seasonsRepository = seasonsRepository;
        this.teamRatingsRepository = teamRatingsRepository;
    }

    @Override
    @Transactional
    public void saveTeamRating(TeamRatingRequestInput teamRatingRequestInput) {
        logger.info("Saving rating for teamId: {}, for matchId: {}, with rating: {}",
                teamRatingRequestInput.getTeamId(),
                teamRatingRequestInput.getMatchId(),
                teamRatingRequestInput.getRating());

        Team team = teamsRepository.findByTeamId(teamRatingRequestInput.getTeamId());
        Match match = matchesRepository.findByMatchId(teamRatingRequestInput.getMatchId());

        if(team != null && match != null) {
            logger.debug("Team and match exist... saving the rating");
            manageTeamRating(teamRatingRequestInput);
        } else {
            throw new IllegalArgumentException("matchId or teamId doesn't match existing data");
        }
    }

    @Override
    public TeamRatingByMatchOutput getTeamRatingByMatch(Integer teamId, Long matchId) {
        logger.info("Getting team rating for teamId: {}, for matchId: {}", teamId, matchId);

        Team team = teamsRepository.findByTeamId(teamId);
        Match match = matchesRepository.findByMatchId(matchId);

        if(team != null && match != null) {
            logger.debug("Team and match exist... getting the rating");
            League league = leaguesRepository.findByLeagueId(match.getLeagueId());
            Season season = seasonsRepository.findBySeasonId(match.getSeasonId());
            return assembler.populateTeamRatingByMatchOutput(team, match, league, season,
                    retrieveAverageTeamRatingByMatch(teamId, matchId));
        } else {
            throw new IllegalArgumentException("matchId or teamId doesn't match existing data");
        }
    }

    @Override
    public TeamRatingsBetweenDatesOutput getTeamRatingsBetweenDates(Integer teamId, Date startDate, Date endDate) {
        logger.info("Getting team rating for teamId: {}, between dates startDate: {} and endDate: {}", teamId,
                startDate, endDate);

        Team team = teamsRepository.findByTeamId(teamId);

        if(team != null) {
            logger.debug("Team exists... getting the ratings");
            List<Match> matches = matchesRepository.findAllByTeamIdAndFixtureDateBetween(teamId, startDate, endDate);
            List<RatingByMatch> teamRatingsByMatch = new ArrayList<>();
            for (Match match : matches) {
                teamRatingsByMatch.add(retrieveTeamRatingByMatch(teamId, match));
            }
            return assembler.populateTeamRatingsBetweenDatesOutput(team, teamRatingsByMatch, startDate, endDate);
        } else {
            throw new IllegalArgumentException("teamId doesn't match existing data");
        }
    }

    private void manageTeamRating(TeamRatingRequestInput teamRatingRequestInput) {
        TeamRating teamRating = new TeamRating();
        teamRating.setMatchId(teamRatingRequestInput.getMatchId());
        teamRating.setTeamId(teamRatingRequestInput.getTeamId());
        teamRating.setRating(teamRatingRequestInput.getRating());
        teamRatingsRepository.save(teamRating);
    }

    private Double retrieveAverageTeamRatingByMatch(Integer teamId, Long matchId) {
        List<TeamRating> teamRatings = teamRatingsRepository.findAllByTeamIdAndMatchId(teamId, matchId);
        Double averageRating = (double) 0;
        for (TeamRating rating : teamRatings) {
            averageRating = averageRating + rating.getRating();
        }
        averageRating = (double) Math.round(averageRating/teamRatings.size() * 100d)/100d;
        return averageRating;
    }

    private RatingByMatch retrieveTeamRatingByMatch(Integer teamId, Match match) {
        League league = leaguesRepository.findByLeagueId(match.getLeagueId());
        Season season = seasonsRepository.findBySeasonId(match.getSeasonId());
        Double rating = retrieveAverageTeamRatingByMatch(teamId, match.getMatchId());
        return assembler.populateRatingByMatch(match, league, season, rating);
    }
}
