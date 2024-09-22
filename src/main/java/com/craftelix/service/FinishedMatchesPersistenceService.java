package com.craftelix.service;

import com.craftelix.dto.*;
import com.craftelix.entity.Match;
import com.craftelix.entity.Player;
import com.craftelix.exception.InvalidParameterException;
import com.craftelix.mapper.FinishedMatchMapper;
import com.craftelix.repository.MatchRepository;
import com.craftelix.util.HibernateUtil;
import lombok.NoArgsConstructor;
import org.hibernate.Session;

import java.util.List;
import java.util.stream.Collectors;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class FinishedMatchesPersistenceService {

    private static final FinishedMatchesPersistenceService INSTANCE = new FinishedMatchesPersistenceService();

    private final PlayerService playerService = PlayerService.getInstance();

    private final FinishedMatchMapper finishedMatchMapper = FinishedMatchMapper.INSTANCE;

    private final int PAGE_SIZE = 10;

    public static FinishedMatchesPersistenceService getInstance() {
        return INSTANCE;
    }

    public MatchFilterResponseDto findByFilter(FilterRequestDto filter, Integer page) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        MatchRepository matchRepository = new MatchRepository(session);

        Long count = matchRepository.countMatchesByFilter(filter);
        if (count <= (long) (page - 1) * PAGE_SIZE) {
            throw new InvalidParameterException("Invalid page number");
        }

        int offset = (page - 1) * PAGE_SIZE;

        List<Match> matches = matchRepository.findMatchesByFilter(filter, offset, PAGE_SIZE);

        long pageCount = (count + PAGE_SIZE - 1) / PAGE_SIZE;

        List<FinishedMatchResponseDto> matchesDto =  matches.stream()
                                                        .map(finishedMatchMapper::toDto)
                                                        .collect(Collectors.toList());

        session.getTransaction().commit();

        return new MatchFilterResponseDto(page, pageCount, filter.getPlayerName(), matchesDto);
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
