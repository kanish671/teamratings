package anishk.developer.teamratings.controllers;

import anishk.developer.teamratings.constants.URLPath;
import anishk.developer.teamratings.dto.ManagersOutput;
import anishk.developer.teamratings.dto.TeamsOutput;
import anishk.developer.teamratings.models.Manager;
import anishk.developer.teamratings.models.Referee;
import anishk.developer.teamratings.responses.Response;
import anishk.developer.teamratings.services.interfaces.IManagerService;
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
@RequestMapping(path = URLPath.BASE_CONTROLLER + URLPath.MANAGER)
public class ManagerRestController {

    private IManagerService managerService;

    @Autowired
    public ManagerRestController(@Qualifier("ManagerService") IManagerService managerService) {
        this.managerService = managerService;
    }

    @ApiOperation(value = "getAllManagers", notes = "Gets all Managers")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Managers Retrieved Successfully", response = Response.class)})
    @GetMapping(path = URLPath.GET_ALL)
    public ResponseEntity<Response<ManagersOutput>> getAllManagers() {
        ManagersOutput managers = managerService.getAllManagers();
        Response<ManagersOutput> apiResponse = Response.<ManagersOutput>builder().responseObj(managers).build();
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @ApiOperation(value = "getManagerByTeam", notes = "Gets Manager for a Team")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Manager Retrieved Successfully", response = Response.class)})
    @GetMapping(path = URLPath.GET_ALL_BY_FILTERS)
    public ResponseEntity<Response<Manager>> getManagerByTeam(@Valid @RequestParam(value = "teamId") Integer teamId) {
        Manager manager = managerService.getManagerByTeam(teamId);
        Response<Manager> apiResponse = Response.<Manager>builder().responseObj(manager).build();
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
}
