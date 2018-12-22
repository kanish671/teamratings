package anishk.developer.teamratings.services.implementations;

import anishk.developer.teamratings.assembler.Assembler;
import anishk.developer.teamratings.dto.RatingByMatch;
import anishk.developer.teamratings.dto.PlayerRatingByMatchOutput;
import anishk.developer.teamratings.dto.PlayerRatingsBetweenDatesOutput;
import anishk.developer.teamratings.dto.PlayerRatingRequestInput;
import anishk.developer.teamratings.models.*;
import anishk.developer.teamratings.repositories.*;
import anishk.developer.teamratings.services.interfaces.IPlayerRatingsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service("PlayerRatingsService")
public class PlayerRatingsService implements IPlayerRatingsService {

    private static Logger logger = LoggerFactory.getLogger(PlayerRatingsService.class);

    private Assembler assembler;
    private PlayersRepository playersRepository;
    private LeaguesRepository leaguesRepository;
    private MatchesRepository matchesRepository;
    private SeasonsRepository seasonsRepository;
    private PlayerRatingsRepository playerRatingsRepository;

    @Autowired
    public PlayerRatingsService(Assembler assembler, PlayersRepository playersRepository,
                                LeaguesRepository leaguesRepository, MatchesRepository matchesRepository,
                                SeasonsRepository seasonsRepository, PlayerRatingsRepository playerRatingsRepository) {
        this.assembler = assembler;
        this.playersRepository = playersRepository;
        this.leaguesRepository = leaguesRepository;
        this.matchesRepository = matchesRepository;
        this.seasonsRepository = seasonsRepository;
        this.playerRatingsRepository = playerRatingsRepository;
    }

    @Override
    @Transactional
    public void savePlayerRating(PlayerRatingRequestInput playerRatingRequestInput) {
        logger.info("Saving rating for playerId: {}, for matchId: {}, with rating: {}",
                playerRatingRequestInput.getPlayerId(),
                playerRatingRequestInput.getMatchId(),
                playerRatingRequestInput.getRating());

        Player player = playersRepository.findByPlayerId(playerRatingRequestInput.getPlayerId());
        Match match = matchesRepository.findByMatchId(playerRatingRequestInput.getMatchId());

        if(player != null && match != null) {
            logger.debug("Player and match exist... saving the rating");
            managePlayerRating(playerRatingRequestInput);
        } else {
            throw new IllegalArgumentException("matchId or playerId doesn't match existing data");
        }
    }

    @Override
    public PlayerRatingByMatchOutput getPlayerRatingByMatch(Long playerId, Long matchId) {
        logger.info("Getting player rating for playerId: {}, for matchId: {}", playerId, matchId);

        Player player = playersRepository.findByPlayerId(playerId);
        Match match = matchesRepository.findByMatchId(matchId);

        if(player != null && match != null) {
            logger.debug("Player and match exist... getting the rating");
            League league = leaguesRepository.findByLeagueId(match.getLeagueId());
            Season season = seasonsRepository.findBySeasonId(match.getSeasonId());
            return assembler.populatePlayerRatingByMatchOutput(player, match, league, season,
                    retrieveAveragePlayerRatingByMatch(playerId, matchId));
        } else {
            throw new IllegalArgumentException("matchId or playerId doesn't match existing data");
        }
    }

    @Override
    public PlayerRatingsBetweenDatesOutput getPlayerRatingsBetweenDates(Long playerId, Date startDate, Date endDate) {
        logger.info("Getting player rating for playerId: {}, between dates startDate: {} and endDate: {}", playerId,
                startDate, endDate);

        Player player = playersRepository.findByPlayerId(playerId);

        if(player != null) {
            logger.debug("Player exists... getting the ratings");
            List<Match> matches = matchesRepository.findAllByTeamIdAndFixtureDateBetweenOrderByFixtureDateAsc(player.getTeamId(), startDate,
                    endDate);
            List<RatingByMatch> playerRatingsByMatch = new ArrayList<>();
            for (Match match : matches) {
                playerRatingsByMatch.add(retrievePlayerRatingByMatch(playerId, match));
            }
            return assembler.populatePlayerRatingsBetweenDatesOutput(player, playerRatingsByMatch, startDate, endDate);
        } else {
            throw new IllegalArgumentException("playerId doesn't match existing data");
        }
    }

    private void managePlayerRating(PlayerRatingRequestInput playerRatingRequestInput) {
        PlayerRating playerRating = new PlayerRating();
        playerRating.setMatchId(playerRatingRequestInput.getMatchId());
        playerRating.setPlayerId(playerRatingRequestInput.getPlayerId());
        playerRating.setRating(playerRatingRequestInput.getRating());
        playerRatingsRepository.save(playerRating);
    }

    private Double retrieveAveragePlayerRatingByMatch(Long playerId, Long matchId) {
        List<PlayerRating> playerRatings = playerRatingsRepository.findAllByPlayerIdAndMatchId(playerId, matchId);
        Double averageRating = (double) 0;
        for (PlayerRating rating : playerRatings) {
            averageRating = averageRating + rating.getRating();
        }
        averageRating = (double) Math.round(averageRating/playerRatings.size() * 100d)/100d;
        return averageRating;
    }

    private RatingByMatch retrievePlayerRatingByMatch(Long playerId, Match match) {
        League league = leaguesRepository.findByLeagueId(match.getLeagueId());
        Season season = seasonsRepository.findBySeasonId(match.getSeasonId());
        Double rating = retrieveAveragePlayerRatingByMatch(playerId, match.getMatchId());
        return assembler.populateRatingByMatch(match, league, season, rating);
    }
}
