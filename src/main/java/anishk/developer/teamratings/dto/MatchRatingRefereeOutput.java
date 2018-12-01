package anishk.developer.teamratings.dto;

import anishk.developer.teamratings.models.Manager;
import anishk.developer.teamratings.models.Referee;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor @AllArgsConstructor
public class MatchRatingRefereeOutput {

    private Referee referee;
    private Double rating;
}
