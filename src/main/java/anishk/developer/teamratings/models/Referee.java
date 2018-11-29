package anishk.developer.teamratings.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "referees")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Referee {

    @Id
    @Column(name = "refereeid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer refereeId;

    @Column(name = "leagueid", nullable = false)
    private Integer leagueId;

    @Column(name = "name", nullable = false)
    @Size(min = 3, max = 50, message = "Referee Name must be between 3 and 50 characters")
    private String name;

    @Column(name = "country")
    @Size(max = 50, message = "Country must be shorter than 50 characters")
    private String country;

    @Column(name = "status", nullable = false)
    @Min(value = 0)
    @Max(value = 1)
    private Integer status;
}
