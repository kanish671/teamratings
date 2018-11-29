package anishk.developer.teamratings.services.interfaces;

import anishk.developer.teamratings.dto.PlayerRatingByMatchOutput;
import anishk.developer.teamratings.dto.PlayerRatingRequestInput;
import anishk.developer.teamratings.dto.PlayerRatingsBetweenDatesOutput;

import java.util.Date;

public interface IPlayerRatingsService {
    void savePlayerRating(PlayerRatingRequestInput playerRatingRequestInput);
    PlayerRatingByMatchOutput getPlayerRatingByMatch(Long playerId, Long matchId);
    PlayerRatingsBetweenDatesOutput getPlayerRatingsBetweenDates(Long playerId, Date startDate, Date endDate);
}
