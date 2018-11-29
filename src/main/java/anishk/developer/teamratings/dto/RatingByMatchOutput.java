package anishk.developer.teamratings.dto;

import anishk.developer.teamratings.models.League;
import anishk.developer.teamratings.models.Match;
import anishk.developer.teamratings.models.Season;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor @AllArgsConstructor
public class RatingByMatchOutput {

    private Match match;
    private League league;
    private Season season;
    private Double rating;
}
