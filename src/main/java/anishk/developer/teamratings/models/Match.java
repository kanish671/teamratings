package anishk.developer.teamratings.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;

@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "matches")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Match {

    @Id
    @Column(name = "matchid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long matchId;

    @Column(name = "seasonid", nullable = false)
    private Integer seasonId;

    @Column(name = "leagueid", nullable = false)
    private Integer leagueId;

    @Column(name = "teamid", nullable = false)
    private Integer teamId;

    @Column(name = "refereeid", nullable = false)
    private Integer refereeId;

    @Column(name = "fixturedate", nullable = false, updatable = false)
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fixtureDate;

    @Column(name = "venue", nullable = false)
    private String venue;

    @Column(name = "opponent", nullable = false)
    private String opponent;

    @Column(name = "result", nullable = false)
    @Size(min = 1, max = 1, message = "Result must be one of W, D or L")
    private Character result;

    @Column(name = "score", nullable = false)
    private String score;
}
