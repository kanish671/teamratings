package anishk.developer.teamratings.dto;

import anishk.developer.teamratings.models.Match;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor @AllArgsConstructor
public class MatchesOutput {

    private List<Match> matches;
}
