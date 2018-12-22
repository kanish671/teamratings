package anishk.developer.teamratings.services.interfaces;

import anishk.developer.teamratings.dto.TeamsOutput;

public interface ITeamService {
    public TeamsOutput getAllTeams();
    public TeamsOutput getAllTeamsByLeague(Integer leagueId);
}
