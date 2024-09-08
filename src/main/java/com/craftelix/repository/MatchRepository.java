package com.craftelix.repository;

import com.craftelix.entity.Match;
import org.hibernate.Session;

public class MatchRepository extends RepositoryBase<Long, Match> {

    public MatchRepository(Session session) {
        super(Match.class, session);
    }
}
