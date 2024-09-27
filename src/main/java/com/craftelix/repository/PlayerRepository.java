package com.craftelix.repository;

import com.craftelix.entity.Player;
import com.craftelix.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.Optional;

public class PlayerRepository extends RepositoryBase<Long, Player> {

    private static final PlayerRepository INSTANCE = new PlayerRepository();

    private PlayerRepository() {
        super(Player.class);
    }

    public static PlayerRepository getInstance() {
        return INSTANCE;
    }

    public Optional<Player> findByName(String name) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Query<Player> query = session.createQuery("from Player where name = :name", Player.class);
        query.setParameter("name", name);
        return query.uniqueResultOptional();
    }
}
