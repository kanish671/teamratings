package anishk.developer.teamratings.repositories;

import anishk.developer.teamratings.models.Statistics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StatisticsRepository extends JpaRepository<Statistics, Long> {
    Statistics findByPlayerIdAndMatchId(Long playerId, Long matchId);
    List<Statistics> findAllByPlayerId();
}
