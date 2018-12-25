package anishk.developer.teamratings.utils;

import anishk.developer.teamratings.dto.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

@Slf4j
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

    public boolean validateTeamRatingRequestInput(TeamRatingRequestInput teamRatingRequestInput) {
        if(longUtils.isNull(teamRatingRequestInput.getMatchId())
                || integerUtils.isNull(teamRatingRequestInput.getTeamId())
                || integerUtils.isNull(teamRatingRequestInput.getRating())) {
            throw new IllegalArgumentException("Invalid Request. One of matchid, teamid or rating was null");
        }
        return true;
    }

    public boolean validateManagerRatingRequestInput(ManagerRatingRequestInput managerRatingRequestInput) {
        if(longUtils.isNull(managerRatingRequestInput.getMatchId())
                || integerUtils.isNull(managerRatingRequestInput.getManagerId())
                || integerUtils.isNull(managerRatingRequestInput.getRating())) {
            throw new IllegalArgumentException("Invalid Request. One of matchid, managerid or rating was null");
        }
        return true;
    }

    public boolean validatePlayerRatingRequestInput(PlayerRatingRequestInput playerRatingRequestInput) {
        if(longUtils.isNull(playerRatingRequestInput.getMatchId())
                || longUtils.isNull(playerRatingRequestInput.getPlayerId())
                || integerUtils.isNull(playerRatingRequestInput.getRating())) {
            throw new IllegalArgumentException("Invalid Request. One of matchid, playerid or rating was null");
        }
        return true;
    }

    public boolean validateRefereeRatingRequestInput(RefereeRatingRequestInput refereeRatingRequestInput) {
        if(longUtils.isNull(refereeRatingRequestInput.getMatchId())
                || integerUtils.isNull(refereeRatingRequestInput.getRefereeId())
                || integerUtils.isNull(refereeRatingRequestInput.getRating())) {
            throw new IllegalArgumentException("Invalid Request. One of matchid, refereeid or rating was null");
        }
        return true;
    }

    public boolean validateMatchRatingRequestInput(MatchRatingRequestInput matchRatingRequestInput) {
        validateTeamRatingRequestInput(matchRatingRequestInput.getTeamRating());
        validateManagerRatingRequestInput(matchRatingRequestInput.getManagerRating());
        validateRefereeRatingRequestInput(matchRatingRequestInput.getRefereeRating());
        for(PlayerRatingRequestInput playerRating : matchRatingRequestInput.getPlayerRatings()) {
            validatePlayerRatingRequestInput(playerRating);
        }
        if(longUtils.isNull(matchRatingRequestInput.getMatchId())) {
            throw new IllegalArgumentException("Invalid Request. matchid was null");
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
