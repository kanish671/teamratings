package anishk.developer.teamratings.controllers;

import anishk.developer.teamratings.constants.URLPath;
import anishk.developer.teamratings.dto.RefereeRatingByMatchOutput;
import anishk.developer.teamratings.dto.RefereeRatingRequestInput;
import anishk.developer.teamratings.dto.RefereeRatingsBetweenDatesOutput;
import anishk.developer.teamratings.services.interfaces.IRefereeRatingsService;
import anishk.developer.teamratings.utils.ValidatorUtils;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;

@RestController
@RequestMapping(path = URLPath.RATINGS_CONTROLLER + URLPath.REFEREE)

public class RefereeRatingsRestController {

    private IRefereeRatingsService refereeRatingsService;
    private ValidatorUtils validatorUtils;

    @Autowired
    public RefereeRatingsRestController(@Qualifier("RefereeRatingsService") IRefereeRatingsService refereeRatingsService,
                                        ValidatorUtils validatorUtils) {
        this.refereeRatingsService = refereeRatingsService;
        this.validatorUtils = validatorUtils;
    }

    @ApiOperation(value = "saveRefereeRating", notes = "Submits rating for a referee for a match")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Referee Rating Saved Successfully", response = String.class)})
    @ConditionalOnExpression("${api.post-enabled:true}")
    @PostMapping(path = URLPath.SAVE_RATING)
    public ResponseEntity<String> saveRefereeRating(@Valid @RequestBody RefereeRatingRequestInput refereeRatingRequestInput) {
        validatorUtils.validateRefereeRatingRequestInput(refereeRatingRequestInput);
        refereeRatingsService.saveRefereeRating(refereeRatingRequestInput);
        return new ResponseEntity<>("Referee Rating Saved Successfully", HttpStatus.CREATED);
    }

    @ApiOperation(value = "getRefereeRatingByMatch", notes = "Gets average rating for a referee for a match")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Referee Rating Retrieved Successfully", response = RefereeRatingByMatchOutput.class)})
    @GetMapping(path = URLPath.GET_RATING)
    public ResponseEntity<RefereeRatingByMatchOutput> getRefereeRatingByMatch(@Valid @RequestParam(value = "refereeId"
    ) Integer refereeId, @Valid @RequestParam(value = "matchId") Long matchId) {
        RefereeRatingByMatchOutput refereeRatingByMatchOutput = refereeRatingsService.getRefereeRatingByMatch(refereeId, matchId);
        return new ResponseEntity<>(refereeRatingByMatchOutput, HttpStatus.OK);
    }

    @ApiOperation(value = "getRefereeRatingsBetweenDates", notes = "Gets average ratings of matches for a referee between two dates")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Referee ratings for the Dates Retrieved Successfully", response =
                    RefereeRatingsBetweenDatesOutput.class)})
    @GetMapping(path = URLPath.GET_RATINGS_BETWEEN_DATES)
    public ResponseEntity<RefereeRatingsBetweenDatesOutput> getRefereeRatingsBetweenDates(@Valid @RequestParam(value = "refereeId"
    ) Integer refereeId, @Valid @RequestParam(value = "startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
            @Valid @RequestParam(value = "endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate) {
        validatorUtils.validateStartDateAndEndDate(startDate, endDate);
        RefereeRatingsBetweenDatesOutput refereeRatingsBetweenDatesOutput =
                refereeRatingsService.getRefereeRatingsBetweenDates(refereeId, startDate, endDate);
        return new ResponseEntity<>(refereeRatingsBetweenDatesOutput, HttpStatus.OK);
    }
}
