package anishk.developer.teamratings.assembler;

import anishk.developer.teamratings.dto.*;
import anishk.developer.teamratings.models.*;
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

    public TeamRatingByMatchOutput populateTeamRatingByMatchOutput(Team team, Match match, League league,
                                                                   Season season, Double rating) {
        TeamRatingByMatchOutput teamRatingByMatchOutput = new TeamRatingByMatchOutput();
        teamRatingByMatchOutput.setTeam(team);
        teamRatingByMatchOutput.setMatch(match);
        teamRatingByMatchOutput.setLeague(league);
        teamRatingByMatchOutput.setSeason(season);
        teamRatingByMatchOutput.setRating(rating);
        return teamRatingByMatchOutput;
    }

    public TeamRatingsBetweenDatesOutput populateTeamRatingsBetweenDatesOutput(Team team,
                                                                               List<RatingByMatch> teamRatingsByMatch, Date startDate, Date endDate) {
        TeamRatingsBetweenDatesOutput teamRatingsBetweenDatesOutput = new TeamRatingsBetweenDatesOutput();
        teamRatingsBetweenDatesOutput.setTeam(team);
        teamRatingsBetweenDatesOutput.setRatingsByMatch(teamRatingsByMatch);
        teamRatingsBetweenDatesOutput.setStartDate(startDate);
        teamRatingsBetweenDatesOutput.setEndDate(endDate);
        return teamRatingsBetweenDatesOutput;
    }

    public ManagerRatingByMatchOutput populateManagerRatingByMatchOutput(Manager manager, Match match, League league,
                                                                         Season season, Double rating) {
        ManagerRatingByMatchOutput managerRatingByMatchOutput = new ManagerRatingByMatchOutput();
        managerRatingByMatchOutput.setManager(manager);
        managerRatingByMatchOutput.setMatch(match);
        managerRatingByMatchOutput.setLeague(league);
        managerRatingByMatchOutput.setSeason(season);
        managerRatingByMatchOutput.setRating(rating);
        return managerRatingByMatchOutput;
    }

    public ManagerRatingsBetweenDatesOutput populateManagerRatingsBetweenDatesOutput(Manager manager,
                        List<RatingByMatch> teamRatingsByMatch, Date startDate, Date endDate) {
        ManagerRatingsBetweenDatesOutput managerRatingsBetweenDatesOutput = new ManagerRatingsBetweenDatesOutput();
        managerRatingsBetweenDatesOutput.setManager(manager);
        managerRatingsBetweenDatesOutput.setRatingsByMatch(teamRatingsByMatch);
        managerRatingsBetweenDatesOutput.setStartDate(startDate);
        managerRatingsBetweenDatesOutput.setEndDate(endDate);
        return managerRatingsBetweenDatesOutput;
    }

    public PlayerRatingByMatchOutput populatePlayerRatingByMatchOutput(Player player, Match match, League league,
                                                                       Season season, Double rating) {
        PlayerRatingByMatchOutput playerRatingByMatchOutput = new PlayerRatingByMatchOutput();
        playerRatingByMatchOutput.setPlayer(player);
        playerRatingByMatchOutput.setMatch(match);
        playerRatingByMatchOutput.setLeague(league);
        playerRatingByMatchOutput.setSeason(season);
        playerRatingByMatchOutput.setRating(rating);
        return playerRatingByMatchOutput;
    }

    public PlayerRatingsBetweenDatesOutput populatePlayerRatingsBetweenDatesOutput(Player player,
                                                                                   List<RatingByMatch> playerRatingsByMatch, Date startDate, Date endDate) {
        PlayerRatingsBetweenDatesOutput playerRatingsBetweenDatesOutput = new PlayerRatingsBetweenDatesOutput();
        playerRatingsBetweenDatesOutput.setPlayer(player);
        playerRatingsBetweenDatesOutput.setRatingsByMatch(playerRatingsByMatch);
        playerRatingsBetweenDatesOutput.setStartDate(startDate);
        playerRatingsBetweenDatesOutput.setEndDate(endDate);
        return playerRatingsBetweenDatesOutput;
    }

    public RefereeRatingByMatchOutput populateRefereeRatingByMatchOutput(Referee referee, Match match, League league,
                                                                         Season season, Double rating) {
        RefereeRatingByMatchOutput refereeRatingByMatchOutput = new RefereeRatingByMatchOutput();
        refereeRatingByMatchOutput.setReferee(referee);
        refereeRatingByMatchOutput.setMatch(match);
        refereeRatingByMatchOutput.setLeague(league);
        refereeRatingByMatchOutput.setSeason(season);
        refereeRatingByMatchOutput.setRating(rating);
        return refereeRatingByMatchOutput;
    }

    public RefereeRatingsBetweenDatesOutput populateRefereeRatingsBetweenDatesOutput(Referee referee,
                                                                                     List<RatingByMatch> refereeRatingsByMatch, Date startDate, Date endDate) {
        RefereeRatingsBetweenDatesOutput refereeRatingsBetweenDatesOutput = new RefereeRatingsBetweenDatesOutput();
        refereeRatingsBetweenDatesOutput.setReferee(referee);
        refereeRatingsBetweenDatesOutput.setRatingsByMatch(refereeRatingsByMatch);
        refereeRatingsBetweenDatesOutput.setStartDate(startDate);
        refereeRatingsBetweenDatesOutput.setEndDate(endDate);
        return refereeRatingsBetweenDatesOutput;
    }

    public RatingByMatch populateRatingByMatch(Match match, League league, Season season, Double rating) {
        RatingByMatch ratingByMatch = new RatingByMatch();
        ratingByMatch.setMatch(match);
        ratingByMatch.setLeague(league);
        ratingByMatch.setSeason(season);
        ratingByMatch.setRating(rating);
        return ratingByMatch;
    }
}
