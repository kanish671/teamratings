package anishk.developer.teamratings.repositories;

import anishk.developer.teamratings.models.Managers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManagersRepository extends JpaRepository<Managers, Integer> {
    Managers findByTeamId(Integer teamId);
}
