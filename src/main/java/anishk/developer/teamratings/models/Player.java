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
@Table(name = "players")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Player {

    @Id
    @Column(name = "playerid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long playerId;

    @Column(name = "teamid", nullable = false)
    private Integer teamId;

    @Column(name = "name", nullable = false)
    @Size(min = 3, max = 50, message = "Player Name must be between 3 and 50 characters")
    private String name;

    @Column(name = "country")
    @Size(max = 50, message = "Country must be shorter than 50 characters")
    private String country;

    @Column(name = "status", nullable = false)
    @Min(value = 0)
    @Max(value = 1)
    private Integer status;

    @Column(name = "number", nullable = false)
    @Min(value = 1)
    @Max(value = 99)
    private Integer number;
}
