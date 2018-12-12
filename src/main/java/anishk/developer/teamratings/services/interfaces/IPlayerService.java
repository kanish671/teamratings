package anishk.developer.teamratings.services.interfaces;

import anishk.developer.teamratings.dto.PlayersOutput;

public interface IPlayerService {
    public PlayersOutput getAllPlayersByTeam(Integer teamId);
}
