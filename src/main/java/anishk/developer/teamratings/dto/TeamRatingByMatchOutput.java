package anishk.developer.teamratings.dto;

import anishk.developer.teamratings.models.Team;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor @AllArgsConstructor
public class TeamRatingByMatchOutput extends RatingByMatchOutput {

    private Team team;
}
