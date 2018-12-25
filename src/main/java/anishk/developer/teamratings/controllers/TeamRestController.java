package anishk.developer.teamratings.controllers;

import anishk.developer.teamratings.constants.URLPath;
import anishk.developer.teamratings.dto.TeamsOutput;
import anishk.developer.teamratings.services.interfaces.ITeamService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(path = URLPath.BASE_CONTROLLER + URLPath.TEAM)
@Slf4j
public class TeamRestController {

    private ITeamService teamService;

    @Autowired
    public TeamRestController(@Qualifier("TeamService") ITeamService teamService) {
        this.teamService = teamService;
    }

    @ApiOperation(value = "getAllTeams", notes = "Gets all Teams")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Teams Retrieved Successfully", response = TeamsOutput.class)})
    @GetMapping(path = URLPath.GET_ALL)
    public ResponseEntity<TeamsOutput> getAllTeams() {
        TeamsOutput teams = teamService.getAllTeams();
        return new ResponseEntity<>(teams, HttpStatus.OK);
    }

    @ApiOperation(value = "getAllTeamsByLeague", notes = "Gets all Teams for a league")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Teams Retrieved Successfully", response = TeamsOutput.class)})
    @GetMapping(path = URLPath.GET_ALL_BY_FILTERS)
    public ResponseEntity<TeamsOutput> getAllTeamsByLeague(@Valid @RequestParam(value = "leagueId") Integer leagueId) {
        TeamsOutput teams = teamService.getAllTeamsByLeague(leagueId);
        return new ResponseEntity<>(teams, HttpStatus.OK);
    }
}
