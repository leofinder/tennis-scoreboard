package com.craftelix.repository;

import com.craftelix.entity.Player;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.Optional;

public class PlayerRepository extends RepositoryBase<Long, Player> {

    public PlayerRepository(Session session) {
        super(Player.class, session);
    }

    public Optional<Player> findByName(String name) {
        Query<Player> query = session.createQuery("from Player where name = :name", Player.class);
        query.setParameter("name", name);
        return query.uniqueResultOptional();
    }
}
