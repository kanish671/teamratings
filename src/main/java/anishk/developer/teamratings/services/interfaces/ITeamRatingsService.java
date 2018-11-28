package anishk.developer.teamratings.services.interfaces;

import anishk.developer.teamratings.dto.TeamRatingByMatchOutput;
import anishk.developer.teamratings.dto.TeamRatingsBetweenDatesOutput;
import anishk.developer.teamratings.dto.TeamRatingsRequestInput;

import java.util.Date;

public interface ITeamRatingsService {
    void saveTeamRating(TeamRatingsRequestInput teamRatingsRequestInput);
    TeamRatingByMatchOutput getTeamRatingByMatch(Integer teamId, Long matchId);
    TeamRatingsBetweenDatesOutput getTeamRatingsBetweenDates(Integer teamId, Date startDate, Date endDate);
}
