package anishk.developer.teamratings.services.interfaces;

import anishk.developer.teamratings.dto.MatchRatingsOutput;

public interface IMatchRatingsService {
    MatchRatingsOutput getMatchRatings(Long matchId);
}
