package anishk.developer.teamratings.services.interfaces;

import anishk.developer.teamratings.dto.LeaguesOutput;

public interface ILeagueService {
    public LeaguesOutput getAllLeaguesByTeam(Integer teamId);
}
