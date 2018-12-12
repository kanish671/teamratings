package anishk.developer.teamratings.dto;

import anishk.developer.teamratings.models.Player;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor @AllArgsConstructor
public class PlayersOutput {

    private List<Player> players;
}
