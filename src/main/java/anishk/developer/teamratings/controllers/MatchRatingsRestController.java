package anishk.developer.teamratings.controllers;

import anishk.developer.teamratings.constants.URLPath;
import anishk.developer.teamratings.dto.MatchRatingRequestInput;
import anishk.developer.teamratings.dto.MatchRatingsOutput;
import anishk.developer.teamratings.services.interfaces.IMatchRatingsService;
import anishk.developer.teamratings.utils.ValidatorUtils;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = URLPath.RATINGS_CONTROLLER + URLPath.MATCH)
@Slf4j
public class MatchRatingsRestController {

    private IMatchRatingsService matchRatingsService;
    private ValidatorUtils validatorUtils;

    @Autowired
    public MatchRatingsRestController(@Qualifier("MatchRatingsService") IMatchRatingsService matchRatingsService,
                                      ValidatorUtils validatorUtils) {
        this.matchRatingsService = matchRatingsService;
        this.validatorUtils = validatorUtils;
    }

    @ApiOperation(value = "saveMatchRatings", notes = "Saves all ratings for a match")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Match Ratings Saved Successfully", response = String.class)})
    @ConditionalOnExpression("${api.post-enabled:true}")
    @PostMapping(path = URLPath.SAVE_RATING)
    public ResponseEntity<String> saveMatchRatings(@Valid @RequestBody MatchRatingRequestInput matchRatingRequestInput) {
        validatorUtils.validateMatchRatingRequestInput(matchRatingRequestInput);
        matchRatingsService.saveMatchRating(matchRatingRequestInput);
        return new ResponseEntity<>("Match Ratings Saved Successfully", HttpStatus.CREATED);
    }

    @ApiOperation(value = "getMatchRatings", notes = "Gets all ratings for a match")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Match Ratings Retrieved Successfully", response = MatchRatingsOutput.class)})
    @GetMapping(path = URLPath.GET_RATING)
    public ResponseEntity<MatchRatingsOutput> getMatchRatings(@Valid @RequestParam(value = "matchId"
    ) Long matchId) {
        MatchRatingsOutput matchRatingsOutput = matchRatingsService.getMatchRatings(matchId);
        return new ResponseEntity<>(matchRatingsOutput, HttpStatus.OK);
    }
}
