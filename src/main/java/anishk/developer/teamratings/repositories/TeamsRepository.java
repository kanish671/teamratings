package anishk.developer.teamratings.repositories;

import anishk.developer.teamratings.models.Teams;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamsRepository extends JpaRepository<Teams, Integer> {
    Teams findByTeamId(Integer teamId);
    Teams findByShortName(String shortName);
}
