package anishk.developer.teamratings.dto;

import anishk.developer.teamratings.models.Referee;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor @AllArgsConstructor
public class RefereeRatingByMatchOutput extends RatingByMatchOutput {

    private Referee referee;
}
