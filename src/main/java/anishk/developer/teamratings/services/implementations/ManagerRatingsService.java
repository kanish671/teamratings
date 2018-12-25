package anishk.developer.teamratings.services.implementations;

import anishk.developer.teamratings.assembler.Assembler;
import anishk.developer.teamratings.dto.ManagerRatingByMatchOutput;
import anishk.developer.teamratings.dto.ManagerRatingRequestInput;
import anishk.developer.teamratings.dto.ManagerRatingsBetweenDatesOutput;
import anishk.developer.teamratings.dto.RatingByMatch;
import anishk.developer.teamratings.models.*;
import anishk.developer.teamratings.repositories.*;
import anishk.developer.teamratings.services.interfaces.IManagerRatingsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service("ManagerRatingsService")
@Slf4j
public class ManagerRatingsService implements IManagerRatingsService {

    private Assembler assembler;
    private TeamsRepository teamsRepository;
    private ManagersRepository managersRepository;
    private LeaguesRepository leaguesRepository;
    private MatchesRepository matchesRepository;
    private SeasonsRepository seasonsRepository;
    private ManagerRatingsRepository managerRatingsRepository;

    @Autowired
    public ManagerRatingsService(Assembler assembler,
                                 TeamsRepository teamsRepository, ManagersRepository managersRepository,
                              LeaguesRepository leaguesRepository, MatchesRepository matchesRepository,
                              SeasonsRepository seasonsRepository, ManagerRatingsRepository managerRatingsRepository) {
        this.assembler = assembler;
        this.teamsRepository = teamsRepository;
        this.managersRepository = managersRepository;
        this.leaguesRepository = leaguesRepository;
        this.matchesRepository = matchesRepository;
        this.seasonsRepository = seasonsRepository;
        this.managerRatingsRepository = managerRatingsRepository;
    }

    @Override
    @Transactional
    public void saveManagerRating(ManagerRatingRequestInput managerRatingRequestInput) {
        log.info("Saving rating for managerId: {}, for matchId: {}, with rating: {}",
                managerRatingRequestInput.getManagerId(),
                managerRatingRequestInput.getMatchId(),
                managerRatingRequestInput.getRating());

        Manager manager = managersRepository.findByTeamId(managerRatingRequestInput.getManagerId());
        Match match = matchesRepository.findByMatchId(managerRatingRequestInput.getMatchId());

        if(manager != null && match != null) {
            log.debug("Manager and match exist... saving the rating");
            manageManagerRating(managerRatingRequestInput);
        } else {
            throw new IllegalArgumentException("matchId or managerId doesn't match existing data");
        }
    }

    @Override
    public ManagerRatingByMatchOutput getManagerRatingByMatch(Integer managerId, Long matchId) {
        log.info("Getting team rating for managerId: {}, for matchId: {}", managerId, matchId);

        Manager manager = managersRepository.findByManagerId(managerId);
        Match match = matchesRepository.findByMatchId(matchId);

        if(manager != null && match != null) {
            log.debug("Manager and match exist... getting the rating");
            League league = leaguesRepository.findByLeagueId(match.getLeagueId());
            Season season = seasonsRepository.findBySeasonId(match.getSeasonId());
            return assembler.populateManagerRatingByMatchOutput(manager, match, league, season,
                    retrieveAverageManagerRatingByMatch(managerId, matchId));
        } else {
            throw new IllegalArgumentException("matchId or managerId doesn't match existing data");
        }
    }

    @Override
    public ManagerRatingsBetweenDatesOutput getManagerRatingsBetweenDates(Integer managerId, Date startDate,
                                                                          Date endDate) {
        log.info("Getting team rating for managerId: {}, between dates startDate: {} and endDate: {}", managerId,
                startDate, endDate);

        Manager manager = managersRepository.findByManagerId(managerId);
        Team team = teamsRepository.findByTeamId(manager.getTeamId());

        if(manager != null && team != null) {
            log.debug("Manager exists... getting the ratings");
            List<Match> matches = matchesRepository.findAllByTeamIdAndFixtureDateBetweenOrderByFixtureDateAsc(manager.getTeamId(), startDate,
                    endDate);
            List<RatingByMatch> managerRatingsByMatch = new ArrayList<>();
            for (Match match : matches) {
                managerRatingsByMatch.add(retrieveManagerRatingByMatch(managerId, match));
            }
            return assembler.populateManagerRatingsBetweenDatesOutput(manager, team, managerRatingsByMatch, startDate,
                    endDate);
        } else {
            throw new IllegalArgumentException("managerId doesn't match existing data");
        }
    }

    private void manageManagerRating(ManagerRatingRequestInput managerRatingRequestInput) {
        ManagerRating managerRating = new ManagerRating();
        managerRating.setMatchId(managerRatingRequestInput.getMatchId());
        managerRating.setManagerId(managerRatingRequestInput.getManagerId());
        managerRating.setRating(managerRatingRequestInput.getRating());
        managerRatingsRepository.save(managerRating);
    }

    private Double retrieveAverageManagerRatingByMatch(Integer managerId, Long matchId) {
        List<ManagerRating> managerRatings = managerRatingsRepository.findAllByManagerIdAndMatchId(managerId, matchId);
        Double averageRating = (double) 0;
        for (ManagerRating rating : managerRatings) {
            averageRating = averageRating + rating.getRating();
        }
        averageRating = (double) Math.round(averageRating/managerRatings.size() * 100d)/100d;
        return averageRating;
    }

    private RatingByMatch retrieveManagerRatingByMatch(Integer managerId, Match match) {
        League league = leaguesRepository.findByLeagueId(match.getLeagueId());
        Season season = seasonsRepository.findBySeasonId(match.getSeasonId());
        Double rating = retrieveAverageManagerRatingByMatch(managerId, match.getMatchId());
        return assembler.populateRatingByMatch(match, league, season, rating);
    }
}
