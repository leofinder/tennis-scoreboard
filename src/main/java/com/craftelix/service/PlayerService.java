package com.craftelix.service;

import com.craftelix.entity.Player;
import com.craftelix.repository.PlayerRepository;
import com.craftelix.util.HibernateUtil;
import lombok.NoArgsConstructor;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Optional;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class PlayerService {

    private static final PlayerService INSTANCE = new PlayerService();

    private final PlayerRepository playerRepository = PlayerRepository.getInstance();

    public static PlayerService getInstance() {
        return INSTANCE;
    }

    public Player saveOrFindPlayer(Player player) {
        return savePlayer(player)
                .orElseGet(() -> findPlayer(player));
    }

    private Optional<Player> savePlayer(Player player) {
        Player savedPlayer = null;

        Transaction transaction = null;

        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();

            savedPlayer = playerRepository.save(player);

            transaction.commit();
        } catch (RuntimeException e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return Optional.ofNullable(savedPlayer);
    }

    public Player findPlayer(Player player) {
        Transaction transaction = null;

        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();

            Optional<Player> maybePlayer= playerRepository.findByName(player.getName());

            transaction.commit();

            return maybePlayer
                    .orElseThrow(() -> new RuntimeException(String.format("Player %s not found", player.getName())));
        } catch (RuntimeException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw e;
        }
    }
}
