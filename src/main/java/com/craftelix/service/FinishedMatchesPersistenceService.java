package com.craftelix.service;

import com.craftelix.dto.*;
import com.craftelix.entity.Match;
import com.craftelix.entity.Player;
import com.craftelix.exception.NotFoundException;
import com.craftelix.mapper.FinishedMatchMapper;
import com.craftelix.repository.MatchRepository;
import com.craftelix.util.HibernateUtil;
import lombok.NoArgsConstructor;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.stream.Collectors;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class FinishedMatchesPersistenceService {

    private static final FinishedMatchesPersistenceService INSTANCE = new FinishedMatchesPersistenceService();

    private final int PAGE_SIZE = 7;

    private final PlayerService playerService = PlayerService.getInstance();

    private final MatchRepository matchRepository = MatchRepository.getInstance();

    private final FinishedMatchMapper finishedMatchMapper = FinishedMatchMapper.INSTANCE;

    public static FinishedMatchesPersistenceService getInstance() {
        return INSTANCE;
    }

    public MatchFilterResponseDto findByFilter(FilterRequestDto filter, Integer page) {

        Transaction transaction = null;

        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();

            Long count = matchRepository.countMatchesByFilter(filter);
            page = page < 1 ? 1 : page;
            int offset = (page - 1) * PAGE_SIZE;
            int pagesCount = count == 0 ? 1 : (int) (count + PAGE_SIZE - 1) / PAGE_SIZE;

            if (page > pagesCount) {
                throw new NotFoundException(String.format("Page number %d is greater than page size %d", page, pagesCount));
            }

            List<Match> matches = matchRepository.findMatchesByFilter(filter, offset, PAGE_SIZE);
            List<FinishedMatchResponseDto> matchesDto = matches.stream()
                    .map(finishedMatchMapper::toDto)
                    .collect(Collectors.toList());

            transaction.commit();

            return new MatchFilterResponseDto(page, pagesCount, filter.getPlayerName(), matchesDto);

        } catch (RuntimeException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw e;
        }
    }

    public void save(FinishedMatchRequestDto finishedMatchRequestDto) {

        Match match = finishedMatchMapper.toEntity(finishedMatchRequestDto);

        Player playerOne = playerService.saveOrFindPlayer(match.getPlayerOne());
        Player playerTwo = playerService.saveOrFindPlayer(match.getPlayerTwo());
        Player winner = playerService.findPlayer(match.getWinner());

        Transaction transaction = null;

        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();

            matchRepository.save(Match.builder()
                    .playerOne(playerOne)
                    .playerTwo(playerTwo)
                    .winner(winner)
                    .build());

            transaction.commit();
        } catch (RuntimeException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw e;
        }
    }

}
