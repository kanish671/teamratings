package anishk.developer.teamratings.dto;

import anishk.developer.teamratings.models.Manager;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor @AllArgsConstructor
public class ManagersOutput {

    private List<Manager> managers;
}
