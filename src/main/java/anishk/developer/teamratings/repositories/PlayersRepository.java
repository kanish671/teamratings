package anishk.developer.teamratings.repositories;

import anishk.developer.teamratings.models.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayersRepository extends JpaRepository<Player, Long> {
    Player findByPlayerId(Long playerId);
    List<Player> findAllByTeamId(Integer teamId);
    Player findByTeamIdAndNumber(Integer teamId, Integer number);
}
