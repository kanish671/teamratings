package anishk.developer.teamratings.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor @AllArgsConstructor
public class PlayerRatingRequestInput extends RatingRequestInput {

    private Long playerId;
}
