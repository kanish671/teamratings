package anishk.developer.teamratings.dto;

import anishk.developer.teamratings.models.Manager;
import anishk.developer.teamratings.models.Team;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor @AllArgsConstructor
public class ManagerRatingsBetweenDatesOutput extends RatingsBetweenDatesOutput {

    private Manager manager;
    private Team team;
}
