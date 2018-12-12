package anishk.developer.teamratings.services.implementations;

import anishk.developer.teamratings.assembler.Assembler;
import anishk.developer.teamratings.dto.ManagersOutput;
import anishk.developer.teamratings.models.Manager;
import anishk.developer.teamratings.models.Team;
import anishk.developer.teamratings.repositories.LeaguesRepository;
import anishk.developer.teamratings.repositories.ManagersRepository;
import anishk.developer.teamratings.repositories.SeasonsRepository;
import anishk.developer.teamratings.repositories.TeamsRepository;
import anishk.developer.teamratings.services.interfaces.IManagerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("ManagerService")
public class ManagerService implements IManagerService {

    private static Logger logger = LoggerFactory.getLogger(ManagerService.class);

    private Assembler assembler;
    private TeamsRepository teamsRepository;
    private ManagersRepository managersRepository;

    @Autowired
    public ManagerService(Assembler assembler, TeamsRepository teamsRepository, ManagersRepository managersRepository) {
        this.assembler = assembler;
        this.teamsRepository = teamsRepository;
        this.managersRepository = managersRepository;
    }

    @Override
    public ManagersOutput getAllManagers() {
        List<Manager> managers = managersRepository.findAll();
        return new ManagersOutput(managers);
    }

    @Override
    public Manager getManagerByTeam(Integer teamId) {
        return managersRepository.findByTeamId(teamId);
    }
}
