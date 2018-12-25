package anishk.developer.teamratings.services.implementations;

import anishk.developer.teamratings.assembler.Assembler;
import anishk.developer.teamratings.dto.ManagersOutput;
import anishk.developer.teamratings.models.Manager;
import anishk.developer.teamratings.repositories.ManagersRepository;
import anishk.developer.teamratings.repositories.TeamsRepository;
import anishk.developer.teamratings.services.interfaces.IManagerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("ManagerService")
@Slf4j
public class ManagerService implements IManagerService {

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
