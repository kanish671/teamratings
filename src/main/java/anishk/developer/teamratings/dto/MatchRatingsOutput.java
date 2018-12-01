package anishk.developer.teamratings.dto;

import anishk.developer.teamratings.models.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor @AllArgsConstructor
public class MatchRatingsOutput {

    private Match match;
    private League league;
    private Season season;
    private MatchRatingManagerOutput managerRating;
    private MatchRatingTeamOutput teamRating;
    private MatchRatingRefereeOutput refereeRating;
    private List<MatchRatingPlayerOutput> playerRatings;
}
