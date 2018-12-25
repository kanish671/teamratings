package anishk.developer.teamratings.controllers;

import anishk.developer.teamratings.constants.URLPath;
import anishk.developer.teamratings.dto.ManagerRatingByMatchOutput;
import anishk.developer.teamratings.dto.ManagerRatingRequestInput;
import anishk.developer.teamratings.dto.ManagerRatingsBetweenDatesOutput;
import anishk.developer.teamratings.services.interfaces.IManagerRatingsService;
import anishk.developer.teamratings.utils.ValidatorUtils;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
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
@RequestMapping(path = URLPath.RATINGS_CONTROLLER + URLPath.MANAGER)
@Slf4j
public class ManagerRatingsRestController {

    private IManagerRatingsService managerRatingsService;
    private ValidatorUtils validatorUtils;

    @Autowired
    public ManagerRatingsRestController(@Qualifier("ManagerRatingsService") IManagerRatingsService managerRatingsService,
                                     ValidatorUtils validatorUtils) {
        this.managerRatingsService = managerRatingsService;
        this.validatorUtils = validatorUtils;
    }

    @ApiOperation(value = "saveManagerRating", notes = "Submits rating for a manager for a match")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Manager Rating Saved Successfully", response = String.class)})
    @ConditionalOnExpression("${api.post-enabled:true}")
    @PostMapping(path = URLPath.SAVE_RATING)
    public ResponseEntity<String> saveManagerRating(@Valid @RequestBody ManagerRatingRequestInput managerRatingRequestInput) {
        validatorUtils.validateManagerRatingRequestInput(managerRatingRequestInput);
        managerRatingsService.saveManagerRating(managerRatingRequestInput);
        return new ResponseEntity<>("Manager Rating Saved Successfully", HttpStatus.CREATED);
    }

    @ApiOperation(value = "getManagerRatingByMatch", notes = "Gets average rating for a manager for a match")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Manager Rating Retrieved Successfully", response = ManagerRatingByMatchOutput.class)})
    @GetMapping(path = URLPath.GET_RATING)
    public ResponseEntity<ManagerRatingByMatchOutput> getManagerRatingByMatch(@Valid @RequestParam(value = "managerId"
    ) Integer managerId, @Valid @RequestParam(value = "matchId") Long matchId) {
        ManagerRatingByMatchOutput managerRatingByMatchOutput = managerRatingsService.getManagerRatingByMatch(managerId, matchId);
        return new ResponseEntity<>(managerRatingByMatchOutput, HttpStatus.OK);
    }

    @ApiOperation(value = "getManagerRatingsBetweenDates", notes = "Gets average ratings of matches for a manager between two dates")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Manager ratings for the Dates Retrieved Successfully", response =
                    ManagerRatingsBetweenDatesOutput.class)})
    @GetMapping(path = URLPath.GET_RATINGS_BETWEEN_DATES)
    public ResponseEntity<ManagerRatingsBetweenDatesOutput> getManagerRatingsBetweenDates(@Valid @RequestParam(value = "managerId"
    ) Integer managerId, @Valid @RequestParam(value = "startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
                                                                                                    @Valid @RequestParam(value = "endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate) {
        validatorUtils.validateStartDateAndEndDate(startDate, endDate);
        ManagerRatingsBetweenDatesOutput managerRatingsBetweenDatesOutput =
                managerRatingsService.getManagerRatingsBetweenDates(managerId, startDate, endDate);
        return new ResponseEntity<>(managerRatingsBetweenDatesOutput, HttpStatus.OK);
    }
}
