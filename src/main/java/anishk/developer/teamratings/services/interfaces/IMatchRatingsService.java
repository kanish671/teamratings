package anishk.developer.teamratings.services.interfaces;

import anishk.developer.teamratings.dto.MatchRatingRequestInput;
import anishk.developer.teamratings.dto.MatchRatingsOutput;

public interface IMatchRatingsService {
    void saveMatchRating(MatchRatingRequestInput matchRatingRequestInput);
    MatchRatingsOutput getMatchRatings(Long matchId);
}
