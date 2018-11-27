package anishk.developer.teamratings.services.interfaces;

import anishk.developer.teamratings.dto.TeamRatingsRequestInput;

public interface ITeamRatingsService {
    void saveTeamRating(TeamRatingsRequestInput teamRatingsRequestInput);
}
