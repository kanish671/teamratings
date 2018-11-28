package anishk.developer.teamratings.dto;

import anishk.developer.teamratings.models.Leagues;
import anishk.developer.teamratings.models.Matches;
import anishk.developer.teamratings.models.Seasons;
import anishk.developer.teamratings.models.Teams;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor @AllArgsConstructor
public class TeamRatingByMatchOutput {

    private Matches match;
    private Teams team;
    private Leagues league;
    private Seasons season;
    private Double rating;
}
