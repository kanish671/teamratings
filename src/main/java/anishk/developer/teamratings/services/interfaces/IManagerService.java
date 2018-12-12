package anishk.developer.teamratings.services.interfaces;

import anishk.developer.teamratings.dto.ManagersOutput;
import anishk.developer.teamratings.models.Manager;

public interface IManagerService {
    public ManagersOutput getAllManagers();
    public Manager getManagerByTeam(Integer teamId);
}
