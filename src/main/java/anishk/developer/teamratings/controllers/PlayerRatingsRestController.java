package anishk.developer.teamratings.controllers;

import anishk.developer.teamratings.constants.URLPath;
import anishk.developer.teamratings.dto.PlayerRatingByMatchOutput;
import anishk.developer.teamratings.dto.PlayerRatingsBetweenDatesOutput;
import anishk.developer.teamratings.dto.PlayerRatingRequestInput;
import anishk.developer.teamratings.responses.Response;
import anishk.developer.teamratings.services.interfaces.IPlayerRatingsService;
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
@RequestMapping(path = URLPath.RATINGS_CONTROLLER_URL + URLPath.PLAYER)
public class PlayerRatingsRestController {

    private IPlayerRatingsService playerRatingsService;
    private ValidatorUtils validatorUtils;

    @Autowired
    public PlayerRatingsRestController(@Qualifier("PlayerRatingsService") IPlayerRatingsService playerRatingsService,
                                       ValidatorUtils validatorUtils) {
        this.playerRatingsService = playerRatingsService;
        this.validatorUtils = validatorUtils;
    }

    @ApiOperation(value = "savePlayerRating", notes = "Submits rating for a player for a match")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Player Rating Saved Successfully", response = Response.class)})
    @PostMapping(path = URLPath.SAVE_RATING)
    public ResponseEntity<Response<String>> savePlayerRating(@Valid @RequestBody PlayerRatingRequestInput playerRatingRequestInput) {
        validatorUtils.validatePlayerRatingRequestInput(playerRatingRequestInput);
        playerRatingsService.savePlayerRating(playerRatingRequestInput);
        Response<String> apiResponse = Response.<String>builder().responseObj("Player Rating Saved Successfully").build();
        return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
    }

    @ApiOperation(value = "getPlayerRatingByMatch", notes = "Gets average rating for a player for a match")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Player Rating Retrieved Successfully", response = Response.class)})
    @GetMapping(path = URLPath.GET_RATING)
    public ResponseEntity<Response<PlayerRatingByMatchOutput>> getPlayerRatingByMatch(@Valid @RequestParam(value = "playerId"
    ) Long playerId, @Valid @RequestParam(value = "matchId") Long matchId) {
        PlayerRatingByMatchOutput playerRatingByMatchOutput = playerRatingsService.getPlayerRatingByMatch(playerId, matchId);
        Response<PlayerRatingByMatchOutput> apiResponse =
                Response.<PlayerRatingByMatchOutput>builder().responseObj(playerRatingByMatchOutput).build();
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @ApiOperation(value = "getPlayerRatingsBetweenDates", notes = "Gets average ratings of matches for a player between two dates")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Player ratings for the Dates Retrieved Successfully", response =
                    Response.class)})
    @GetMapping(path = URLPath.GET_RATINGS_BETWEEN_DATES)
    public ResponseEntity<Response<PlayerRatingsBetweenDatesOutput>> getPlayerRatingsBetweenDates(@Valid @RequestParam(value = "playerId"
    ) Long playerId, @Valid @RequestParam(value = "startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
                                                                                                  @Valid @RequestParam(value = "endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate) {
        validatorUtils.validateStartDateAndEndDate(startDate, endDate);
        PlayerRatingsBetweenDatesOutput playerRatingsBetweenDatesOutput =
                playerRatingsService.getPlayerRatingsBetweenDates(playerId, startDate, endDate);
        Response<PlayerRatingsBetweenDatesOutput> apiResponse =
                Response.<PlayerRatingsBetweenDatesOutput>builder().responseObj(playerRatingsBetweenDatesOutput).build();
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
}
