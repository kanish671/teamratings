package anishk.developer.teamratings.controllers;

import anishk.developer.teamratings.constants.URLPath;
import anishk.developer.teamratings.dto.TeamRatingsRequestInput;
import anishk.developer.teamratings.responses.Response;
import anishk.developer.teamratings.services.interfaces.ITeamRatingsService;
import anishk.developer.teamratings.utils.ValidatorUtils;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(path = URLPath.RATINGS_CONTROLLER_URL)
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
            @ApiResponse(code = 200, message = "Team Rating Saved Successfully", response = Response.class)})
    @PostMapping(path = URLPath.SAVE_TEAM_RATING)
    public ResponseEntity<Response<String>> saveTeamRating(@Valid @RequestBody TeamRatingsRequestInput teamRatingsRequestInput) {
        validatorUtils.validateTeamRatingRequestInput(teamRatingsRequestInput);
        teamRatingsService.saveTeamRating(teamRatingsRequestInput);
        Response<String> apiResponse = Response.<String>builder().responseObj("Team Rating Saved Successfully").build();
        return new ResponseEntity<Response<String>>(apiResponse, HttpStatus.CREATED);
    }
}
