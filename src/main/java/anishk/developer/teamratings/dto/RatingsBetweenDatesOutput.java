package anishk.developer.teamratings.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor @AllArgsConstructor
public class RatingsBetweenDatesOutput {

    private List<RatingByMatch> ratingsByMatch;
    private Date startDate;
    private Date endDate;
}
