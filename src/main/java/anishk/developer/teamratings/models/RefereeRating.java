package anishk.developer.teamratings.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.Date;

@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "refereeratings")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RefereeRating {

    @Id
    @Column(name = "ratingid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ratingId;

    @Column(name = "matchid", nullable = false)
    private Long matchId;

    @Column(name = "refereeid", nullable = false)
    private Integer refereeId;

    @Column(name = "rating", nullable = false)
    @Min(value = 0, message = "Rating must be between 0 and 10")
    @Max(value = 10, message = "Rating must be between 0 and 10")
    private Integer rating;

    @Column(name = "createdtime", nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @CreatedDate
    private Date createdTime;
}
