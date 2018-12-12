package anishk.developer.teamratings.controllers;

import anishk.developer.teamratings.constants.URLPath;
import anishk.developer.teamratings.dto.LeaguesOutput;
import anishk.developer.teamratings.responses.Response;
import anishk.developer.teamratings.services.interfaces.ILeagueService;
import anishk.developer.teamratings.utils.ValidatorUtils;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
public class LeagueRestController {

    private ILeagueService leagueService;

    @Autowired
    public LeagueRestController(@Qualifier("LeagueService") ILeagueService leagueService) {
        this.leagueService = leagueService;
    }

    @ApiOperation(value = "getAllLeaguesByTeam", notes = "Gets all Leagues for a team ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Leagues Retrieved Successfully", response = Response.class)})
    @GetMapping(path = URLPath.GET_ALL_BY_FILTERS)
    public ResponseEntity<Response<LeaguesOutput>> getAllLeaguesByTeam(@Valid @RequestParam(value = "teamId") Integer teamId) {
        LeaguesOutput leagues= leagueService.getAllLeaguesByTeam(teamId);
        Response<LeaguesOutput> apiResponse = Response.<LeaguesOutput>builder().responseObj(leagues).build();
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
}
