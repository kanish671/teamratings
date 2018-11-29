package anishk.developer.teamratings.services.interfaces;

import anishk.developer.teamratings.dto.RefereeRatingByMatchOutput;
import anishk.developer.teamratings.dto.RefereeRatingRequestInput;
import anishk.developer.teamratings.dto.RefereeRatingsBetweenDatesOutput;

import java.util.Date;

public interface IRefereeRatingsService {
    void saveRefereeRating(RefereeRatingRequestInput refereeRatingRequestInput);
    RefereeRatingByMatchOutput getRefereeRatingByMatch(Integer refereeId, Long matchId);
    RefereeRatingsBetweenDatesOutput getRefereeRatingsBetweenDates(Integer refereeId, Date startDate, Date endDate);
}
