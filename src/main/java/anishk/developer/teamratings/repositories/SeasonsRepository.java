package anishk.developer.teamratings.repositories;

import anishk.developer.teamratings.models.Season;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeasonsRepository extends JpaRepository<Season, Integer> {
    Season findBySeasonId(Integer seasonId);
}
