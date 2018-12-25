package anishk.developer.teamratings.services.implementations;

import anishk.developer.teamratings.assembler.Assembler;
import anishk.developer.teamratings.dto.RatingByMatch;
import anishk.developer.teamratings.dto.RefereeRatingByMatchOutput;
import anishk.developer.teamratings.dto.RefereeRatingRequestInput;
import anishk.developer.teamratings.dto.RefereeRatingsBetweenDatesOutput;
import anishk.developer.teamratings.models.*;
import anishk.developer.teamratings.repositories.*;
import anishk.developer.teamratings.services.interfaces.IRefereeRatingsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service("RefereeRatingsService")
@Slf4j
public class RefereeRatingsService implements IRefereeRatingsService {

    private Assembler assembler;
    private RefereesRepository refereesRepository;
    private LeaguesRepository leaguesRepository;
    private MatchesRepository matchesRepository;
    private SeasonsRepository seasonsRepository;
    private RefereeRatingsRepository refereeRatingsRepository;

    @Autowired
    public RefereeRatingsService(Assembler assembler, RefereesRepository refereesRepository,
                                 LeaguesRepository leaguesRepository, MatchesRepository matchesRepository,
                                 SeasonsRepository seasonsRepository, RefereeRatingsRepository refereeRatingsRepository) {
        this.assembler = assembler;
        this.refereesRepository = refereesRepository;
        this.leaguesRepository = leaguesRepository;
        this.matchesRepository = matchesRepository;
        this.seasonsRepository = seasonsRepository;
        this.refereeRatingsRepository = refereeRatingsRepository;
    }

    @Override
    @Transactional
    public void saveRefereeRating(RefereeRatingRequestInput refereeRatingRequestInput) {
        log.info("Saving rating for refereeId: {}, for matchId: {}, with rating: {}",
                refereeRatingRequestInput.getRefereeId(),
                refereeRatingRequestInput.getMatchId(),
                refereeRatingRequestInput.getRating());

        Referee referee = refereesRepository.findByRefereeId(refereeRatingRequestInput.getRefereeId());
        Match match = matchesRepository.findByMatchId(refereeRatingRequestInput.getMatchId());

        if(referee != null && match != null) {
            log.debug("Referee and match exist... saving the rating");
            manageRefereeRating(refereeRatingRequestInput);
        } else {
            throw new IllegalArgumentException("matchId or refereeId doesn't match existing data");
        }
    }

    @Override
    public RefereeRatingByMatchOutput getRefereeRatingByMatch(Integer refereeId, Long matchId) {
        log.info("Getting referee rating for refereeId: {}, for matchId: {}", refereeId, matchId);

        Referee referee = refereesRepository.findByRefereeId(refereeId);
        Match match = matchesRepository.findByMatchId(matchId);

        if(referee != null && match != null) {
            log.debug("Referee and match exist... getting the rating");
            League league = leaguesRepository.findByLeagueId(match.getLeagueId());
            Season season = seasonsRepository.findBySeasonId(match.getSeasonId());
            return assembler.populateRefereeRatingByMatchOutput(referee, match, league, season,
                    retrieveAverageRefereeRatingByMatch(refereeId, matchId));
        } else {
            throw new IllegalArgumentException("matchId or refereeId doesn't match existing data");
        }
    }

    @Override
    public RefereeRatingsBetweenDatesOutput getRefereeRatingsBetweenDates(Integer refereeId, Date startDate, Date endDate) {
        log.info("Getting referee rating for refereeId: {}, between dates startDate: {} and endDate: {}", refereeId,
                startDate, endDate);

        Referee referee = refereesRepository.findByRefereeId(refereeId);

        if(referee != null) {
            log.debug("Referee exists... getting the ratings");
            List<Match> matches = matchesRepository.findAllByRefereeIdAndFixtureDateBetween(refereeId,
                    startDate, endDate);
            List<RatingByMatch> refereeRatingsByMatch = new ArrayList<>();
            for (Match match : matches) {
                refereeRatingsByMatch.add(retrieveRefereeRatingByMatch(refereeId, match));
            }
            return assembler.populateRefereeRatingsBetweenDatesOutput(referee, refereeRatingsByMatch, startDate, endDate);
        } else {
            throw new IllegalArgumentException("refereeId doesn't match existing data");
        }
    }

    private void manageRefereeRating(RefereeRatingRequestInput refereeRatingRequestInput) {
        RefereeRating refereeRating = new RefereeRating();
        refereeRating.setMatchId(refereeRatingRequestInput.getMatchId());
        refereeRating.setRefereeId(refereeRatingRequestInput.getRefereeId());
        refereeRating.setRating(refereeRatingRequestInput.getRating());
        refereeRatingsRepository.save(refereeRating);
    }

    private Double retrieveAverageRefereeRatingByMatch(Integer refereeId, Long matchId) {
        List<RefereeRating> refereeRatings = refereeRatingsRepository.findAllByRefereeIdAndMatchId(refereeId, matchId);
        Double averageRating = (double) 0;
        for (RefereeRating rating : refereeRatings) {
            averageRating = averageRating + rating.getRating();
        }
        averageRating = (double) Math.round(averageRating/refereeRatings.size() * 100d)/100d;
        return averageRating;
    }

    private RatingByMatch retrieveRefereeRatingByMatch(Integer refereeId, Match match) {
        League league = leaguesRepository.findByLeagueId(match.getLeagueId());
        Season season = seasonsRepository.findBySeasonId(match.getSeasonId());
        Double rating = retrieveAverageRefereeRatingByMatch(refereeId, match.getMatchId());
        return assembler.populateRatingByMatch(match, league, season, rating);
    }
}
