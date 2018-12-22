package anishk.developer.teamratings.controllers;

import anishk.developer.teamratings.constants.URLPath;
import anishk.developer.teamratings.dto.RefereesOutput;
import anishk.developer.teamratings.models.Referee;
import anishk.developer.teamratings.services.interfaces.IRefereeService;
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
@RequestMapping(path = URLPath.BASE_CONTROLLER + URLPath.REFEREE)
public class RefereeRestController {

    private IRefereeService refereeService;

    @Autowired
    public RefereeRestController(@Qualifier("RefereeService") IRefereeService refereeService) {
        this.refereeService = refereeService;
    }

    @ApiOperation(value = "getAllReferees", notes = "Gets all Referees")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Referees Retrieved Successfully", response = RefereesOutput.class)})
    @GetMapping(path = URLPath.GET_ALL)
    public ResponseEntity<RefereesOutput> getAllReferees() {
        RefereesOutput referees = refereeService.getAllReferees();
        return new ResponseEntity<>(referees, HttpStatus.OK);
    }

    @ApiOperation(value = "getRefereeByMatch", notes = "Gets Referee for a Match")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Referee Retrieved Successfully", response = Referee.class)})
    @GetMapping(path = URLPath.GET_ALL_BY_FILTERS)
    public ResponseEntity<Referee> getRefereeByMatch(@Valid @RequestParam(value = "matchId") Long matchId) {
        Referee referee = refereeService.getRefereeByMatch(matchId);
        return new ResponseEntity<>(referee, HttpStatus.OK);
    }
}
