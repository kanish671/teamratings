package anishk.developer.teamratings.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor @AllArgsConstructor
public class RatingRequestInput {

    private Long matchId;
    private Integer rating;
}
