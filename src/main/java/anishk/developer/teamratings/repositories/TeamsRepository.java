package anishk.developer.teamratings.repositories;

import anishk.developer.teamratings.models.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamsRepository extends JpaRepository<Team, Integer> {
    Team findByTeamId(Integer teamId);
    Team findByShortName(String shortName);
    List<Team> findAllByTeamIdIn(List<Integer> teamIds);
}
