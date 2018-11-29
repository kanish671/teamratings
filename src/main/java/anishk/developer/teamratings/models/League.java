package anishk.developer.teamratings.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.Size;

@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "leagues")
@Data
@NoArgsConstructor @AllArgsConstructor
@Builder
public class League {

    @Id
    @Column(name = "leagueid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer leagueId;

    @Column(name = "name", nullable = false)
    @Size(min = 3, max = 50, message = "League Name must be between 3 and 50 characters")
    private String name;
}
