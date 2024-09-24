package com.craftelix.service;

import com.craftelix.entity.MatchScore;
import com.craftelix.entity.PlayerScore;
import com.craftelix.entity.PlayerType;
import com.craftelix.entity.Point;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class MatchScoreCalculationService {

    private static final MatchScoreCalculationService INSTANCE = new MatchScoreCalculationService();

    private final int GAMES_PER_SET = 6;

    private final int POINTS_IN_TIEBREAK = 7;

    private final int SETS_TO_WIN = 2;

    public static MatchScoreCalculationService getInstance() {
        return INSTANCE;
    }

    private boolean isTiebreak(PlayerScore playerOneScore, PlayerScore playerTwoScore) {
        return playerOneScore.getGame() == GAMES_PER_SET && playerTwoScore.getGame() == GAMES_PER_SET;
    }

    private void updateScoreAfterPoint(PlayerScore winnerScore, PlayerScore loserScore) {
        if (isTiebreak(winnerScore, loserScore)) {
            updateTieBreakScore(winnerScore, loserScore);
        } else {
            updatePointScore(winnerScore, loserScore);
        }
    }

    private void updateTieBreakScore(PlayerScore winnerScore, PlayerScore loserScore) {
        int winnerTieBreak = winnerScore.getTiebreak();
        int loserTieBreak = loserScore.getTiebreak();

        if (winnerTieBreak >= POINTS_IN_TIEBREAK - 1 && winnerTieBreak > loserTieBreak) {
            winnerScore.setTiebreak(0);
            loserScore.setTiebreak(0);
            updateGameScore(winnerScore, loserScore);
        } else {
            winnerScore.setTiebreak(winnerTieBreak + 1);
        }
    }

    private void updatePointScore(PlayerScore winnerScore, PlayerScore loserScore) {
        Point winnerPoints = winnerScore.getPoint();
        Point loserPoints = loserScore.getPoint();

        if (winnerPoints.compareTo(loserPoints) > 0) {
            if (winnerPoints == Point.POINTS_40 || winnerPoints == Point.POINTS_AD) {
                winnerScore.setPoint(Point.POINTS_0);
                loserScore.setPoint(Point.POINTS_0);
                updateGameScore(winnerScore, loserScore);
            } else {
                winnerScore.setPoint(winnerPoints.next());
            }
        } else if (winnerPoints.compareTo(loserPoints) < 0 && loserPoints == Point.POINTS_AD) {
            loserScore.setPoint(Point.POINTS_40);
        } else {
            winnerScore.setPoint(winnerPoints.next());
        }
    }

    private void updateGameScore(PlayerScore winnerScore, PlayerScore loserScore) {
        int winnerGame = winnerScore.getGame();
        int loserGame = loserScore.getGame();

        if (winnerGame >= GAMES_PER_SET - 1 && winnerGame > loserGame || isTiebreak(winnerScore, loserScore)) {
            winnerScore.setGame(0);
            loserScore.setGame(0);
            updateSetScore(winnerScore);
        } else {
            winnerScore.setGame(winnerGame + 1);
        }
    }

    private void updateSetScore(PlayerScore winnerScore) {
        winnerScore.setSet(winnerScore.getSet() + 1);
    }

    public MatchScore updateMatchPoint(MatchScore score, PlayerType winner) {
        if (score == null) {
            throw new IllegalArgumentException("Score cannot be null");
        }

        if (isMatchOver(score)) {
            return score;
        }

        PlayerScore playerOneScore = score.getPlayerOneScore();
        PlayerScore playerTwoScore = score.getPlayerTwoScore();

        if (winner == PlayerType.PLAYER_ONE) {
            updateScoreAfterPoint(playerOneScore, playerTwoScore);
        } else {
            updateScoreAfterPoint(playerTwoScore, playerOneScore);
        }

        score.setTiebreak(isTiebreak(playerOneScore, playerTwoScore));
        return score;
    }

    public boolean isMatchOver(MatchScore score) {
        return score.getPlayerOneScore().getSet() == SETS_TO_WIN || score.getPlayerTwoScore().getSet() == SETS_TO_WIN;
    }
}