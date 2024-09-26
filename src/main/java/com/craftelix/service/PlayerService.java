package com.craftelix.service;

import com.craftelix.entity.Player;
import com.craftelix.repository.PlayerRepository;
import com.craftelix.util.HibernateUtil;
import lombok.NoArgsConstructor;
import org.hibernate.Session;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class PlayerService {

    private static final PlayerService INSTANCE = new PlayerService();

    private final PlayerRepository playerRepository = PlayerRepository.getInstance();

    public static PlayerService getInstance() {
        return INSTANCE;
    }

    public Player findOrSavePlayer(Player player) {
        return playerRepository.findByName(player.getName())
                .orElseGet(() -> playerRepository.save(player));
    }

}
