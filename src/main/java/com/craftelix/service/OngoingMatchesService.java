package com.craftelix.service;

import com.craftelix.dto.PlayerRequestDto;
import com.craftelix.entity.Match;
import com.craftelix.entity.MatchScore;
import com.craftelix.entity.Player;
import com.craftelix.entity.PlayerScore;
import com.craftelix.mapper.PlayerMapper;
import lombok.NoArgsConstructor;

import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class OngoingMatchesService {

    private static final OngoingMatchesService INSTANCE = new OngoingMatchesService();

    private final ConcurrentMap<UUID, Match> ongoingMatches = new ConcurrentHashMap<>();

    private final PlayerMapper playerMapper = PlayerMapper.INSTANCE;

    public static OngoingMatchesService getInstance() {
        return INSTANCE;
    }

    public UUID createMatch(PlayerRequestDto playerOneRequestDto, PlayerRequestDto playerTwoRequestDto) {
        Player playerOne = playerMapper.toEntity(playerOneRequestDto);
        Player playerTwo = playerMapper.toEntity(playerTwoRequestDto);

        UUID uuid = UUID.randomUUID();

        Match match = Match.builder()
                .playerOne(playerOne)
                .playerTwo(playerTwo)
                .score(new MatchScore(new PlayerScore(), new PlayerScore()))
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
