package com.craftelix.service;

import com.craftelix.dto.FinishedMatchRequestDto;
import com.craftelix.entity.Match;
import com.craftelix.entity.Player;
import com.craftelix.mapper.FinishedMatchMapper;
import com.craftelix.repository.MatchRepository;
import com.craftelix.util.HibernateUtil;
import lombok.NoArgsConstructor;
import org.hibernate.Session;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class FinishedMatchesPersistenceService {

    private static final FinishedMatchesPersistenceService INSTANCE = new FinishedMatchesPersistenceService();

    private final PlayerService playerService = PlayerService.getInstance();

    private final FinishedMatchMapper finishedMatchMapper = FinishedMatchMapper.INSTANCE;

    public static FinishedMatchesPersistenceService getInstance() {
        return INSTANCE;
    }

    public void save(FinishedMatchRequestDto finishedMatchRequestDto) {
        Match match = finishedMatchMapper.toEntity(finishedMatchRequestDto);

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        Player playerOne = playerService.findOrSavePlayerInRepository(session, match.getPlayerOne());
        Player playerTwo = playerService.findOrSavePlayerInRepository(session, match.getPlayerTwo());
        Player winner = playerService.findOrSavePlayerInRepository(session, match.getWinner());

        MatchRepository matchRepository = new MatchRepository(session);
        matchRepository.save(Match.builder()
                        .playerOne(playerOne)
                        .playerTwo(playerTwo)
                        .winner(winner)
                        .build());

        session.getTransaction().commit();
    }

}
