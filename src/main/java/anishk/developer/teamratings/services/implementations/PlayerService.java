package anishk.developer.teamratings.services.implementations;

import anishk.developer.teamratings.dto.PlayersOutput;
import anishk.developer.teamratings.models.Player;
import anishk.developer.teamratings.repositories.PlayersRepository;
import anishk.developer.teamratings.services.interfaces.IPlayerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("PlayerService")
public class PlayerService implements IPlayerService {

    private static Logger logger = LoggerFactory.getLogger(MatchService.class);

    private PlayersRepository playersRepository;

    @Autowired
    public PlayerService(PlayersRepository playersRepository) {
        this.playersRepository = playersRepository;
    }

    @Override
    public PlayersOutput getAllPlayersByTeam(Integer teamId) {
        List<Player> players = playersRepository.findAllByTeamId(teamId);
        return new PlayersOutput(players);
    }
}
