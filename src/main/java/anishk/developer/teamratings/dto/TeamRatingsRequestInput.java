package anishk.developer.teamratings.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor @AllArgsConstructor
public class TeamRatingsRequestInput {

    private Long matchId;
    private Integer teamId;
    private Integer rating;
}
