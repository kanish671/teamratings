package anishk.developer.teamratings.controllers;

import anishk.developer.teamratings.constants.URLPath;
import anishk.developer.teamratings.dto.LeaguesOutput;
import anishk.developer.teamratings.services.interfaces.ILeagueService;
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
@RequestMapping(path = URLPath.BASE_CONTROLLER + URLPath.LEAGUE)
@Slf4j
public class LeagueRestController {

    private ILeagueService leagueService;

    @Autowired
    public LeagueRestController(@Qualifier("LeagueService") ILeagueService leagueService) {
        this.leagueService = leagueService;
    }

    @ApiOperation(value = "getAllLeagues", notes = "Gets all Leagues ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Leagues Retrieved Successfully", response = LeaguesOutput.class)})
    @GetMapping(path = URLPath.GET_ALL)
    public ResponseEntity<LeaguesOutput> getAllLeagues() {
        LeaguesOutput leagues= leagueService.getAllLeagues();
        return new ResponseEntity<>(leagues, HttpStatus.OK);
    }

    @ApiOperation(value = "getAllLeaguesByTeam", notes = "Gets all Leagues for a team ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Leagues Retrieved Successfully", response = LeaguesOutput.class)})
    @GetMapping(path = URLPath.GET_ALL_BY_FILTERS)
    public ResponseEntity<LeaguesOutput> getAllLeaguesByTeam(@Valid @RequestParam(value = "teamId") Integer teamId) {
        LeaguesOutput leagues= leagueService.getAllLeaguesByTeam(teamId);
        return new ResponseEntity<>(leagues, HttpStatus.OK);
    }
}
