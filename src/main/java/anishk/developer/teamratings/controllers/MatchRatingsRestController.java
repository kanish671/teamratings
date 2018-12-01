package anishk.developer.teamratings.controllers;

import anishk.developer.teamratings.constants.URLPath;
import anishk.developer.teamratings.dto.MatchRatingsOutput;
import anishk.developer.teamratings.dto.TeamRatingByMatchOutput;
import anishk.developer.teamratings.responses.Response;
import anishk.developer.teamratings.services.interfaces.IMatchRatingsService;
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
@RequestMapping(path = URLPath.RATINGS_CONTROLLER_URL + URLPath.MATCH)
public class MatchRatingsRestController {

    private IMatchRatingsService matchRatingsService;
    private ValidatorUtils validatorUtils;

    @Autowired
    public MatchRatingsRestController(@Qualifier("MatchRatingsService") IMatchRatingsService matchRatingsService,
                                      ValidatorUtils validatorUtils) {
        this.matchRatingsService = matchRatingsService;
        this.validatorUtils = validatorUtils;
    }

    @ApiOperation(value = "getMatchRatings", notes = "Gets all ratings for a match")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Match Ratings Retrieved Successfully", response = Response.class)})
    @GetMapping(path = URLPath.GET_RATING)
    public ResponseEntity<Response<MatchRatingsOutput>> getMatchRatings(@Valid @RequestParam(value = "matchId"
    ) Long matchId) {
        MatchRatingsOutput matchRatingsOutput = matchRatingsService.getMatchRatings(matchId);
        Response<MatchRatingsOutput> apiResponse =
                Response.<MatchRatingsOutput>builder().responseObj(matchRatingsOutput).build();
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
}
