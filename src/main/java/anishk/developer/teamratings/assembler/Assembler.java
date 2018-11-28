package anishk.developer.teamratings.assembler;

import anishk.developer.teamratings.dto.TeamRatingByMatch;
import anishk.developer.teamratings.dto.TeamRatingByMatchOutput;
import anishk.developer.teamratings.dto.TeamRatingsBetweenDatesOutput;
import anishk.developer.teamratings.models.Leagues;
import anishk.developer.teamratings.models.Matches;
import anishk.developer.teamratings.models.Seasons;
import anishk.developer.teamratings.models.Teams;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class Assembler {

    private Mapper mapper;

    @Autowired
    public Assembler(Mapper mapper) { this.mapper = mapper; }

    public TeamRatingByMatchOutput populateTeamRatingByMatchOutput(Teams team, Matches match, Leagues league,
                                                                   Seasons season, Double rating) {
        TeamRatingByMatchOutput teamRatingByMatchOutput = new TeamRatingByMatchOutput();
        teamRatingByMatchOutput.setTeam(team);
        teamRatingByMatchOutput.setMatch(match);
        teamRatingByMatchOutput.setLeague(league);
        teamRatingByMatchOutput.setSeason(season);
        teamRatingByMatchOutput.setRating(rating);
        return teamRatingByMatchOutput;
    }

    public TeamRatingsBetweenDatesOutput populateTeamRatingsBetweenDatesOutput(Teams team,
                                List<TeamRatingByMatch> teamRatingsByMatch, Date startDate, Date endDate) {
        TeamRatingsBetweenDatesOutput teamRatingsBetweenDatesOutput = new TeamRatingsBetweenDatesOutput();
        teamRatingsBetweenDatesOutput.setTeam(team);
        teamRatingsBetweenDatesOutput.setTeamRatingsByMatch(teamRatingsByMatch);
        teamRatingsBetweenDatesOutput.setStartDate(startDate);
        teamRatingsBetweenDatesOutput.setEndDate(endDate);
        return teamRatingsBetweenDatesOutput;
    }

    public TeamRatingByMatch populateTeamRatingByMatch(Matches match, Leagues league, Seasons season, Double rating) {
        TeamRatingByMatch teamRatingByMatch = new TeamRatingByMatch();
        teamRatingByMatch.setMatch(match);
        teamRatingByMatch.setLeague(league);
        teamRatingByMatch.setSeason(season);
        teamRatingByMatch.setRating(rating);
        return teamRatingByMatch;
    }
}
