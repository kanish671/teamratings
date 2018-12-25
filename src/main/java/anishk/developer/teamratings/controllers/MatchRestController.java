package anishk.developer.teamratings.controllers;

import anishk.developer.teamratings.constants.URLPath;
import anishk.developer.teamratings.dto.MatchesOutput;
import anishk.developer.teamratings.services.interfaces.IMatchService;
import anishk.developer.teamratings.utils.ValidatorUtils;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Date;

@RestController
@RequestMapping(path = URLPath.BASE_CONTROLLER + URLPath.MATCH)
@Slf4j
public class MatchRestController {

    private IMatchService matchService;
    private ValidatorUtils validatorUtils;

    @Autowired
    public MatchRestController(@Qualifier("MatchService") IMatchService matchService, ValidatorUtils validatorUtils) {
        this.matchService = matchService;
        this.validatorUtils = validatorUtils;
    }

    @ApiOperation(value = "getAllMatchesByTeamBetweenDates", notes = "Gets all Matches for a team between dates")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Matches Retrieved Successfully", response = MatchesOutput.class)})
    @GetMapping(path = URLPath.GET_ALL_BETWEEN_DATES)
    public ResponseEntity<MatchesOutput> getAllMatchesByTeamBetweenDates(@Valid @RequestParam(value = "teamId")
    Integer teamId, @Valid @RequestParam(value = "startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
    @Valid @RequestParam(value = "endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate) {
        validatorUtils.validateStartDateAndEndDate(startDate, endDate);
        MatchesOutput matches = matchService.getAllMatchesByTeamBetweenDates(teamId, startDate, endDate);
        return new ResponseEntity<>(matches, HttpStatus.OK);
    }

    @ApiOperation(value = "getAllMatchesByTeamLeagueAndSeason", notes = "Gets matches for team by league and season")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Matches Retrieved Successfully", response = MatchesOutput.class)})
    @GetMapping(path = URLPath.GET_ALL_BY_FILTERS)
    public ResponseEntity<MatchesOutput> getAllMatchesByTeamLeagueAndSeason(@Valid @RequestParam(value = "teamId")
    Integer teamId, @Valid @RequestParam(value = "leagueId") Integer leagueId, @Valid @RequestParam(value = "seasonId") Integer seasonId) {
        MatchesOutput matches = matchService.getAllMatchesByTeamLeagueAndSeason(teamId, leagueId, seasonId);
        return new ResponseEntity<>(matches, HttpStatus.OK);
    }
}
