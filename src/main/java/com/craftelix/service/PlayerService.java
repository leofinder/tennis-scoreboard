package com.craftelix.service;

import com.craftelix.entity.Player;
import com.craftelix.repository.PlayerRepository;
import com.craftelix.util.HibernateUtil;
import lombok.NoArgsConstructor;
import org.hibernate.Session;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class PlayerService {

    private static PlayerService INSTANCE = new PlayerService();

    public static PlayerService getInstance() {
        return INSTANCE;
    }

    public Player findOrSavePlayer(Session session, Player player) {
        PlayerRepository playerRepository = new PlayerRepository(session);
        return playerRepository.findByName(player.getName())
                .orElseGet(() -> playerRepository.save(player));
    }

    public Player findOrSavePlayer(Player player) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        player = findOrSavePlayer(session, player);

        session.getTransaction().commit();

        return player;
    }

    public void findOrSavePlayers(Player... players) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        for (Player player : players) {
            findOrSavePlayer(session, player);
        }

        session.getTransaction().commit();
    }
}
