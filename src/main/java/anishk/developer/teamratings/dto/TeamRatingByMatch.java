package anishk.developer.teamratings.dto;

import anishk.developer.teamratings.models.Leagues;
import anishk.developer.teamratings.models.Matches;
import anishk.developer.teamratings.models.Seasons;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor @AllArgsConstructor
public class TeamRatingByMatch {

    private Matches match;
    private Leagues league;
    private Seasons season;
    private Double rating;
}
