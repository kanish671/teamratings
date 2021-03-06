package anishk.developer.teamratings.dto;

import anishk.developer.teamratings.models.Player;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor @AllArgsConstructor
public class PlayerRatingsBetweenDatesOutput extends RatingsBetweenDatesOutput {

    private Player player;
}
