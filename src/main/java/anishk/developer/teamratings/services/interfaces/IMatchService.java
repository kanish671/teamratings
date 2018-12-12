package anishk.developer.teamratings.services.interfaces;

import anishk.developer.teamratings.dto.MatchesOutput;

import java.util.Date;

public interface IMatchService {
    public MatchesOutput getAllMatchesByTeamBetweenDates(Integer teamId, Date startDate, Date endDate);
    public MatchesOutput getAllMatchesByTeamLeagueAndSeason(Integer teamId, Integer leagueId, Integer seasonId);
}
