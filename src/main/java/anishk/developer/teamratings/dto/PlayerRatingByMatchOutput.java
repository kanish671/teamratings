package anishk.developer.teamratings.dto;

import anishk.developer.teamratings.models.Player;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor @AllArgsConstructor
public class PlayerRatingByMatchOutput extends RatingByMatchOutput {

    private Player player;
}
