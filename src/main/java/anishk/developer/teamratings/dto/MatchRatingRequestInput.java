package anishk.developer.teamratings.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor @AllArgsConstructor
public class MatchRatingRequestInput {

    private Long matchId;
    private TeamRatingRequestInput teamRating;
    private ManagerRatingRequestInput managerRating;
    private RefereeRatingRequestInput refereeRating;
    private List<PlayerRatingRequestInput> playerRatings;
}
