package anishk.developer.teamratings.controllers;

import anishk.developer.teamratings.constants.URLPath;
import anishk.developer.teamratings.dto.PlayersOutput;
import anishk.developer.teamratings.services.interfaces.IPlayerService;
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
@RequestMapping(path = URLPath.BASE_CONTROLLER + URLPath.PLAYER)
public class PlayerRestController {

    private IPlayerService playerService;

    @Autowired
    public PlayerRestController(@Qualifier("PlayerService") IPlayerService playerService) {
        this.playerService = playerService;
    }

    @ApiOperation(value = "getAllPlayersByTeam", notes = "Gets all Players for a team ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Players Retrieved Successfully", response = PlayersOutput.class)})
    @GetMapping(path = URLPath.GET_ALL_BY_FILTERS)
    public ResponseEntity<PlayersOutput> getAllPlayersByTeam(@Valid @RequestParam(value = "teamId") Integer teamId) {
        PlayersOutput players = playerService.getAllPlayersByTeam(teamId);
        return new ResponseEntity<>(players, HttpStatus.OK);
    }
}
