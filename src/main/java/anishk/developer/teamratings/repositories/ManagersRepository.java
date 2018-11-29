package anishk.developer.teamratings.repositories;

import anishk.developer.teamratings.models.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManagersRepository extends JpaRepository<Manager, Integer> {
    Manager findByTeamId(Integer teamId);
    Manager findByManagerId(Integer managerId);
}
