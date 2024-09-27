package com.craftelix.repository;

import com.craftelix.dto.FilterRequestDto;
import com.craftelix.entity.Match;
import com.craftelix.util.HibernateUtil;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;

import java.util.List;

public class MatchRepository extends RepositoryBase<Long, Match> {

    private static final MatchRepository INSTANCE = new MatchRepository();

    private MatchRepository() {
        super(Match.class);
    }

    public static MatchRepository getInstance() {
        return INSTANCE;
    }

    public List<Match> findMatchesByFilter(FilterRequestDto filter, int offset, int limit) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Match> criteria = cb.createQuery(Match.class);
        Root<Match> match = criteria.from(Match.class);
        Predicate predicate = getPlayerPredicate(cb, match, filter);

        criteria.select(match)
                .where(predicate);

        return session.createQuery(criteria)
                .setFirstResult(offset)
                .setMaxResults(limit)
                .list();
    }

    public Long countMatchesByFilter(FilterRequestDto filter) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Long> criteria = cb.createQuery(Long.class);
        Root<Match> match = criteria.from(Match.class);
        Predicate predicate = getPlayerPredicate(cb, match, filter);

        criteria.select(cb.count(match))
                .where(predicate);

        return session.createQuery(criteria)
                .getSingleResult();
    }

    private Predicate getPlayerPredicate(CriteriaBuilder cb, Root<Match> match, FilterRequestDto filter) {
        return cb.or(
                cb.like(cb.lower(match.get("playerOne").get("name")), "%"  + filter.getPlayerName().toLowerCase() + "%"),
                cb.like(cb.lower(match.get("playerTwo").get("name")), "%"  + filter.getPlayerName().toLowerCase() + "%")
        );
    }
}
