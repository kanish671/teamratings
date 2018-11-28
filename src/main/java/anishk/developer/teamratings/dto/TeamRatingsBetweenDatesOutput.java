package anishk.developer.teamratings.dto;

import anishk.developer.teamratings.models.Leagues;
import anishk.developer.teamratings.models.Seasons;
import anishk.developer.teamratings.models.Teams;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor @AllArgsConstructor
public class TeamRatingsBetweenDatesOutput {

    private Teams team;
    private List<TeamRatingByMatch> teamRatingsByMatch;
    private Date startDate;
    private Date endDate;
}
