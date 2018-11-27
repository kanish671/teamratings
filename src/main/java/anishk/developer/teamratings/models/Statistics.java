package anishk.developer.teamratings.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "statistics")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Statistics {

    @Id
    @Column(name = "statisticid")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long statisticId;

    @Column(name = "playerid", nullable = false)
    private Long playerId;

    @Column(name = "matchid", nullable = false)
    private Long matchId;

    @Column(name = "goals", nullable = false)
    @Min(value = 0, message = "Goals must be between 0 and 100")
    @Max(value = 100, message = "Goals must be between 0 and 100")
    private Integer goals;

    @Column(name = "assists", nullable = false)
    @Min(value = 0, message = "Assists must be between 0 and 100")
    @Max(value = 100, message = "Assits must be between 0 and 100")
    private Integer assists;

    @Column(name = "averagerating", nullable = false)
    private Double averageRating;
}
