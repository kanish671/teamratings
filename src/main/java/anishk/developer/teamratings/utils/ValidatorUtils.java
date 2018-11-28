package anishk.developer.teamratings.utils;

import anishk.developer.teamratings.dto.TeamRatingsRequestInput;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

public class ValidatorUtils {
    private IntegerUtils integerUtils;
    private LongUtils longUtils;
    private DateUtils dateUtils;

    @Autowired
    public ValidatorUtils(IntegerUtils integerUtils, LongUtils longUtils, DateUtils dateUtils) {
        this.integerUtils = integerUtils;
        this.longUtils = longUtils;
        this.dateUtils = dateUtils;
    }

    public boolean validateTeamRatingRequestInput(TeamRatingsRequestInput teamRatingsRequestInput) {
        if(longUtils.isNull(teamRatingsRequestInput.getMatchId())
                || integerUtils.isNull(teamRatingsRequestInput.getTeamId())
                || integerUtils.isNull(teamRatingsRequestInput.getRating())) {
            throw new IllegalArgumentException("Invalid Request. One of matchid, teamid or rating was null");
        }
        return true;
    }

    public boolean validateStartDateAndEndDate(Date startDate, Date endDate) {
        if(dateUtils.isNull(startDate) || dateUtils.isNull(endDate) || startDate.after(endDate)) {
            throw new IllegalArgumentException("Invalid Request. Either of the dates is null or start date is after " +
                    "end date");
        }
        return true;
    }
}
