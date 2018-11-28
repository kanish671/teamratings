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
@Table(name = "seasons")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Seasons {

    @Id
    @Column(name = "seasonid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer seasonId;

    @Column(name = "season", nullable = false)
    @Size(min = 4, max = 20, message = "Season must be between 4 and 20 characters")
    private String season;
}
