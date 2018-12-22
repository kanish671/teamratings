package anishk.developer.teamratings.controllers;

import anishk.developer.teamratings.constants.URLPath;
import anishk.developer.teamratings.dto.PlayerRatingByMatchOutput;
import anishk.developer.teamratings.dto.PlayerRatingRequestInput;
import anishk.developer.teamratings.dto.PlayerRatingsBetweenDatesOutput;
import anishk.developer.teamratings.services.interfaces.IPlayerRatingsService;
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
@RequestMapping(path = URLPath.RATINGS_CONTROLLER + URLPath.PLAYER)
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
            @ApiResponse(code = 200, message = "Player Rating Saved Successfully", response = String.class)})
    @ConditionalOnExpression("${api.post-enabled:true}")
    @PostMapping(path = URLPath.SAVE_RATING)
    public ResponseEntity<String> savePlayerRating(@Valid @RequestBody PlayerRatingRequestInput playerRatingRequestInput) {
        validatorUtils.validatePlayerRatingRequestInput(playerRatingRequestInput);
        playerRatingsService.savePlayerRating(playerRatingRequestInput);
        return new ResponseEntity<>("Player Rating Saved Successfully", HttpStatus.CREATED);
    }

    @ApiOperation(value = "getPlayerRatingByMatch", notes = "Gets average rating for a player for a match")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Player Rating Retrieved Successfully", response = PlayerRatingByMatchOutput.class)})
    @GetMapping(path = URLPath.GET_RATING)
    public ResponseEntity<PlayerRatingByMatchOutput> getPlayerRatingByMatch(@Valid @RequestParam(value = "playerId"
    ) Long playerId, @Valid @RequestParam(value = "matchId") Long matchId) {
        PlayerRatingByMatchOutput playerRatingByMatchOutput = playerRatingsService.getPlayerRatingByMatch(playerId, matchId);
        return new ResponseEntity<>(playerRatingByMatchOutput, HttpStatus.OK);
    }

    @ApiOperation(value = "getPlayerRatingsBetweenDates", notes = "Gets average ratings of matches for a player between two dates")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Player ratings for the Dates Retrieved Successfully", response =
                    PlayerRatingsBetweenDatesOutput.class)})
    @GetMapping(path = URLPath.GET_RATINGS_BETWEEN_DATES)
    public ResponseEntity<PlayerRatingsBetweenDatesOutput> getPlayerRatingsBetweenDates(@Valid @RequestParam(value = "playerId"
    ) Long playerId, @Valid @RequestParam(value = "startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
    @Valid @RequestParam(value = "endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate) {
        validatorUtils.validateStartDateAndEndDate(startDate, endDate);
        PlayerRatingsBetweenDatesOutput playerRatingsBetweenDatesOutput =
                playerRatingsService.getPlayerRatingsBetweenDates(playerId, startDate, endDate);
        return new ResponseEntity<>(playerRatingsBetweenDatesOutput, HttpStatus.OK);
    }
}
