package anishk.developer.teamratings.dto;

import anishk.developer.teamratings.models.Referee;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor @AllArgsConstructor
public class RefereesOutput {

    private List<Referee> referees;
}
