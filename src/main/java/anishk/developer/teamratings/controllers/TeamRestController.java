package anishk.developer.teamratings.controllers;

import anishk.developer.teamratings.constants.URLPath;
import anishk.developer.teamratings.dto.TeamsOutput;
import anishk.developer.teamratings.responses.Response;
import anishk.developer.teamratings.services.interfaces.ITeamService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = URLPath.BASE_CONTROLLER + URLPath.TEAM)
public class TeamRestController {

    private ITeamService teamService;

    @Autowired
    public TeamRestController(@Qualifier("TeamService") ITeamService teamService) {
        this.teamService = teamService;
    }

    @ApiOperation(value = "getAllTeams", notes = "Gets all Teams")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Teams Retrieved Successfully", response = Response.class)})
    @GetMapping(path = URLPath.GET_ALL)
    public ResponseEntity<Response<TeamsOutput>> getAllTeams() {
        TeamsOutput teams = teamService.getAllTeams();
        Response<TeamsOutput> apiResponse = Response.<TeamsOutput>builder().responseObj(teams).build();
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
}
