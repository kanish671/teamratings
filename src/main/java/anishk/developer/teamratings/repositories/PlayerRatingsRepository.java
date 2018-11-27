package anishk.developer.teamratings.repositories;

import anishk.developer.teamratings.models.PlayerRatings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayerRatingsRepository extends JpaRepository<PlayerRatings, Long> {
    List<PlayerRatings> findAllByMatchIdAndPlayerId(Long matchId, Integer playerId);
    List<PlayerRatings> findAllByMatchId(Integer matchId);
    List<PlayerRatings> findAllByPlayerId(Integer playerId);
}
