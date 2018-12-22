package anishk.developer.teamratings.controllers;

import anishk.developer.teamratings.constants.URLPath;
import anishk.developer.teamratings.dto.TeamRatingByMatchOutput;
import anishk.developer.teamratings.dto.TeamRatingRequestInput;
import anishk.developer.teamratings.dto.TeamRatingsBetweenDatesOutput;
import anishk.developer.teamratings.services.interfaces.ITeamRatingsService;
import anishk.developer.teamratings.utils.ValidatorUtils;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;

@RestController
@RequestMapping(path = URLPath.RATINGS_CONTROLLER + URLPath.TEAM)
public class TeamRatingsRestController {

    private ITeamRatingsService teamRatingsService;
    private ValidatorUtils validatorUtils;

    @Autowired
    public TeamRatingsRestController(@Qualifier("TeamRatingsService") ITeamRatingsService teamRatingsService,
                                     ValidatorUtils validatorUtils) {
        this.teamRatingsService = teamRatingsService;
        this.validatorUtils = validatorUtils;
    }

    @ApiOperation(value = "saveTeamRating", notes = "Submits rating for a team for a match")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Team Rating Saved Successfully", response = String.class)})
    @ConditionalOnProperty(name = "api.post-enabled", havingValue = "true")
    @PostMapping(path = URLPath.SAVE_RATING)
    public ResponseEntity<String> saveTeamRating(@Valid @RequestBody TeamRatingRequestInput teamRatingRequestInput) {
        validatorUtils.validateTeamRatingRequestInput(teamRatingRequestInput);
        teamRatingsService.saveTeamRating(teamRatingRequestInput);
        return new ResponseEntity<>("Team Rating Saved Successfully", HttpStatus.CREATED);
    }

    @ApiOperation(value = "getTeamRatingByMatch", notes = "Gets average rating for a team for a match")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Team Rating Retrieved Successfully", response = TeamRatingByMatchOutput.class)})
    @GetMapping(path = URLPath.GET_RATING)
    public ResponseEntity<TeamRatingByMatchOutput> getTeamRatingByMatch(@Valid @RequestParam(value = "teamId"
    ) Integer teamId, @Valid @RequestParam(value = "matchId") Long matchId) {
        TeamRatingByMatchOutput teamRatingByMatchOutput = teamRatingsService.getTeamRatingByMatch(teamId, matchId);
        return new ResponseEntity<>(teamRatingByMatchOutput, HttpStatus.OK);
    }

    @ApiOperation(value = "getTeamRatingsBetweenDates", notes = "Gets average ratings of matches for a team between two dates")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Team ratings for the Dates Retrieved Successfully", response =
                    TeamRatingsBetweenDatesOutput.class)})
    @GetMapping(path = URLPath.GET_RATINGS_BETWEEN_DATES)
    public ResponseEntity<TeamRatingsBetweenDatesOutput> getTeamRatingsBetweenDates(@Valid @RequestParam(value = "teamId"
    ) Integer teamId, @Valid @RequestParam(value = "startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
            @Valid @RequestParam(value = "endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate) {
        validatorUtils.validateStartDateAndEndDate(startDate, endDate);
        TeamRatingsBetweenDatesOutput teamRatingsBetweenDatesOutput =
                teamRatingsService.getTeamRatingsBetweenDates(teamId, startDate, endDate);
        return new ResponseEntity<>(teamRatingsBetweenDatesOutput, HttpStatus.OK);
    }
}
