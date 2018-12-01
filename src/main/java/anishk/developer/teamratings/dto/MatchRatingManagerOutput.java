package anishk.developer.teamratings.dto;

import anishk.developer.teamratings.models.Manager;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor @AllArgsConstructor
public class MatchRatingManagerOutput {

    private Manager manager;
    private Double rating;
}
