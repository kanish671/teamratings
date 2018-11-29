package anishk.developer.teamratings.repositories;

import anishk.developer.teamratings.models.PlayerRating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayerRatingsRepository extends JpaRepository<PlayerRating, Long> {
    List<PlayerRating> findAllByPlayerIdAndMatchId(Long playerId, Long matchId);
    List<PlayerRating> findAllByMatchId(Integer matchId);
    List<PlayerRating> findAllByPlayerId(Integer playerId);
}
