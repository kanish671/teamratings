package anishk.developer.teamratings.dto;

import anishk.developer.teamratings.models.League;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor @AllArgsConstructor
public class LeaguesOutput {

    private List<League> leagues;
}
