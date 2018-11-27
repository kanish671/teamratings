package anishk.developer.teamratings.repositories;

import anishk.developer.teamratings.models.Players;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayersRepository extends JpaRepository<Players, Integer> {
    List<Players> findAllByTeamId(Integer teamId);
    Players findByTeamIdAndNumber(Integer teamId, Integer number);
}
