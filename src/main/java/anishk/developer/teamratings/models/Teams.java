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
@Table(name = "teams")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Teams {

    @Id
    @Column(name = "teamid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer teamId;

    @Column(name = "name", nullable = false)
    @Size(min = 3, max = 50, message = "Team Name must be between 3 and 50 characters")
    private String name;

    @Column(name = "shortname", nullable = false)
    @Size(min = 1, max = 3, message = "Team Short Name must be shorter than 3 characters")
    private String shortName;

    @Column(name = "city")
    @Size(max = 50, message = "City must be shorter than 50 characters")
    private String city;
}
