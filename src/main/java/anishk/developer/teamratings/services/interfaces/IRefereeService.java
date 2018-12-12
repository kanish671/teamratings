package anishk.developer.teamratings.services.interfaces;

import anishk.developer.teamratings.dto.RefereesOutput;
import anishk.developer.teamratings.models.Referee;

public interface IRefereeService {
    public RefereesOutput getAllReferees();
    public Referee getRefereeByMatch(Long matchId);
}
