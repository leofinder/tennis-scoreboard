package com.craftelix.repository;

import com.craftelix.entity.Player;
import org.hibernate.SessionFactory;

public class PlayerRepository extends RepositoryBase<Long, Player> {

    public PlayerRepository(SessionFactory sessionFactory) {
        super(Player.class, sessionFactory);
    }

}
