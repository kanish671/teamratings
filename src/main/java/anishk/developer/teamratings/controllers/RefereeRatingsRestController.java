package anishk.developer.teamratings.controllers;

import anishk.developer.teamratings.constants.URLPath;
import anishk.developer.teamratings.dto.RefereeRatingByMatchOutput;
import anishk.developer.teamratings.dto.RefereeRatingsBetweenDatesOutput;
import anishk.developer.teamratings.dto.RefereeRatingRequestInput;
import anishk.developer.teamratings.responses.Response;
import anishk.developer.teamratings.services.interfaces.IRefereeRatingsService;
import anishk.developer.teamratings.utils.ValidatorUtils;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
            @ApiResponse(code = 200, message = "Referee Rating Saved Successfully", response = Response.class)})
    @PostMapping(path = URLPath.SAVE_RATING)
    public ResponseEntity<Response<String>> saveRefereeRating(@Valid @RequestBody RefereeRatingRequestInput refereeRatingRequestInput) {
        validatorUtils.validateRefereeRatingRequestInput(refereeRatingRequestInput);
        refereeRatingsService.saveRefereeRating(refereeRatingRequestInput);
        Response<String> apiResponse = Response.<String>builder().responseObj("Referee Rating Saved Successfully").build();
        return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
    }

    @ApiOperation(value = "getRefereeRatingByMatch", notes = "Gets average rating for a referee for a match")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Referee Rating Retrieved Successfully", response = Response.class)})
    @GetMapping(path = URLPath.GET_RATING)
    public ResponseEntity<Response<RefereeRatingByMatchOutput>> getRefereeRatingByMatch(@Valid @RequestParam(value = "refereeId"
    ) Integer refereeId, @Valid @RequestParam(value = "matchId") Long matchId) {
        RefereeRatingByMatchOutput refereeRatingByMatchOutput = refereeRatingsService.getRefereeRatingByMatch(refereeId, matchId);
        Response<RefereeRatingByMatchOutput> apiResponse =
                Response.<RefereeRatingByMatchOutput>builder().responseObj(refereeRatingByMatchOutput).build();
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @ApiOperation(value = "getRefereeRatingsBetweenDates", notes = "Gets average ratings of matches for a referee between two dates")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Referee ratings for the Dates Retrieved Successfully", response =
                    Response.class)})
    @GetMapping(path = URLPath.GET_RATINGS_BETWEEN_DATES)
    public ResponseEntity<Response<RefereeRatingsBetweenDatesOutput>> getRefereeRatingsBetweenDates(@Valid @RequestParam(value = "refereeId"
    ) Integer refereeId, @Valid @RequestParam(value = "startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
            @Valid @RequestParam(value = "endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate) {
        validatorUtils.validateStartDateAndEndDate(startDate, endDate);
        RefereeRatingsBetweenDatesOutput refereeRatingsBetweenDatesOutput =
                refereeRatingsService.getRefereeRatingsBetweenDates(refereeId, startDate, endDate);
        Response<RefereeRatingsBetweenDatesOutput> apiResponse =
                Response.<RefereeRatingsBetweenDatesOutput>builder().responseObj(refereeRatingsBetweenDatesOutput).build();
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
}
