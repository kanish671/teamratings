package anishk.developer.teamratings.dto;

import anishk.developer.teamratings.models.Team;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor @AllArgsConstructor
public class TeamsOutput {

    private List<Team> teams;
}
