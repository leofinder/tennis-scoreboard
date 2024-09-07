package com.craftelix.repository;

import com.craftelix.entity.Match;
import org.hibernate.SessionFactory;

public class MatchRepository extends RepositoryBase<Long, Match> {

    public MatchRepository(SessionFactory sessionFactory) {
        super(Match.class, sessionFactory);
    }
}
