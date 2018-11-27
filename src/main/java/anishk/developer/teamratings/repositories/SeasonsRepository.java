package anishk.developer.teamratings.repositories;

import anishk.developer.teamratings.models.Seasons;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeasonsRepository extends JpaRepository<Seasons, Integer> {
    Seasons findBySeasonId(Integer seasonId);
}
