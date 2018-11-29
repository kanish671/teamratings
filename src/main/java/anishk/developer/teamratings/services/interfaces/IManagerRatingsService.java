package anishk.developer.teamratings.services.interfaces;

import anishk.developer.teamratings.dto.ManagerRatingByMatchOutput;
import anishk.developer.teamratings.dto.ManagerRatingRequestInput;
import anishk.developer.teamratings.dto.ManagerRatingsBetweenDatesOutput;

import java.util.Date;

public interface IManagerRatingsService {
    void saveManagerRating(ManagerRatingRequestInput managerRatingRequestInput);
    ManagerRatingByMatchOutput getManagerRatingByMatch(Integer managerId, Long matchId);
    ManagerRatingsBetweenDatesOutput getManagerRatingsBetweenDates(Integer managerId, Date startDate, Date endDate);
}
