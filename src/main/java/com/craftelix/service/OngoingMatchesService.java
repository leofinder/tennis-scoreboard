package com.craftelix.service;

import com.craftelix.dto.PlayerRequestDto;
import com.craftelix.entity.Match;
import com.craftelix.entity.Player;
import com.craftelix.mapper.PlayerMapper;
import com.craftelix.repository.PlayerRepository;
import com.craftelix.util.HibernateUtil;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.Session;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class OngoingMatchesService {

    @Getter(lazy = true)
    private static final OngoingMatchesService instance = new OngoingMatchesService();

    private final Map<UUID, Match> ongoingMatches = new HashMap<>();

    private final PlayerMapper playerMapper = PlayerMapper.INSTANCE;

    private void savePlayers(Player player1, Player player2) {

        Session session = HibernateUtil.getSession();

        session.beginTransaction();

        PlayerRepository playerRepository = new PlayerRepository(session);
        if (playerRepository.findByName(player1.getName()).isEmpty()) {
            playerRepository.save(player1);
        }
        if (playerRepository.findByName(player2.getName()).isEmpty()) {
            playerRepository.save(player2);
        }

        session.getTransaction().commit();
    }

    public UUID createMatch(PlayerRequestDto playerRequestDto1, PlayerRequestDto playerRequestDto2) {

        Player player1 = playerMapper.toEntity(playerRequestDto1);
        Player player2 = playerMapper.toEntity(playerRequestDto2);

        savePlayers(player1, player2);

        UUID uuid = UUID.randomUUID();

        Match match = Match.builder()
                .player1(player1)
                .player2(player2)
                .build();

        ongoingMatches.put(uuid, match);

        return uuid;
    }

    public Optional<Match> getMatch(UUID uuid) {
        return Optional.ofNullable(ongoingMatches.get(uuid));
    }

    public void deleteMatch(UUID uuid) {
        ongoingMatches.remove(uuid);
    }
}
