package anishk.developer.teamratings.utils;

import anishk.developer.teamratings.dto.TeamRatingsRequestInput;
import org.springframework.beans.factory.annotation.Autowired;

public class ValidatorUtils {
    private IntegerUtils integerUtils;

    @Autowired
    public ValidatorUtils(IntegerUtils integerUtils) { this.integerUtils = integerUtils; }

    public boolean validateTeamRatingRequestInput(TeamRatingsRequestInput teamRatingsRequestInput) {
        if(integerUtils.isNull(teamRatingsRequestInput.getMatchid())
                || integerUtils.isNull(teamRatingsRequestInput.getTeamid())
                || integerUtils.isNull(teamRatingsRequestInput.getRating())) {
            throw new IllegalArgumentException("Invalid Request. One of matchid, teamid or rating was null");
        }
        return true;
    }
}
