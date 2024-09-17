package com.craftelix.service;

import com.craftelix.entity.MatchScore;
import com.craftelix.entity.PlayerScore;
import com.craftelix.entity.PlayerType;
import com.craftelix.entity.Point;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class MatchScoreCalculationServiceTest {

    private final MatchScoreCalculationService matchScoreCalculationService = MatchScoreCalculationService.getInstance();

    @Test
    void whenPlayerOneScores_thenScoreIs15Love() {
        PlayerScore playerOneScore = new PlayerScore(0, 0, Point.POINTS_0, 0);
        PlayerScore playerTwoScore = new PlayerScore(0, 0, Point.POINTS_0, 0);
        MatchScore score = new MatchScore(playerOneScore, playerTwoScore);

        MatchScore result = matchScoreCalculationService.updateMatchPoint(score, PlayerType.PLAYER_ONE);

        PlayerScore playerOneScoreExpected = new PlayerScore(0, 0, Point.POINTS_15, 0);
        PlayerScore playerTwoScoreExpected = new PlayerScore(0, 0, Point.POINTS_0, 0);
        MatchScore expected = new MatchScore(playerOneScoreExpected, playerTwoScoreExpected);

        assertThat(result).isEqualTo(expected);
    }

    @Test
    void whenPlayerTwoScores_thenScoreIsLove15() {
        PlayerScore playerOneScore = new PlayerScore(0, 0, Point.POINTS_0, 0);
        PlayerScore playerTwoScore = new PlayerScore(0, 0, Point.POINTS_0, 0);
        MatchScore score = new MatchScore(playerOneScore, playerTwoScore);

        MatchScore result = matchScoreCalculationService.updateMatchPoint(score, PlayerType.PLAYER_TWO);

        PlayerScore playerOneScoreExpected = new PlayerScore(0, 0, Point.POINTS_0, 0);
        PlayerScore playerTwoScoreExpected = new PlayerScore(0, 0, Point.POINTS_15, 0);
        MatchScore expected = new MatchScore(playerOneScoreExpected, playerTwoScoreExpected);

        assertThat(result).isEqualTo(expected);
    }

    @Test
    void whenBothPlayersScoreOnce_thenScoreIs15All() {
        PlayerScore playerOneScore = new PlayerScore(0, 0, Point.POINTS_15, 0);
        PlayerScore playerTwoScore = new PlayerScore(0, 0, Point.POINTS_0, 0);
        MatchScore score = new MatchScore(playerOneScore, playerTwoScore);

        MatchScore result = matchScoreCalculationService.updateMatchPoint(score, PlayerType.PLAYER_TWO);

        PlayerScore playerOneScoreExpected = new PlayerScore(0, 0, Point.POINTS_15, 0);
        PlayerScore playerTwoScoreExpected = new PlayerScore(0, 0, Point.POINTS_15, 0);
        MatchScore expected = new MatchScore(playerOneScoreExpected, playerTwoScoreExpected);

        assertThat(result).isEqualTo(expected);
    }

    @Test
    void whenPlayerOneScoresTwice_thenScoreIs30Love() {
        PlayerScore playerOneScore = new PlayerScore(0, 0, Point.POINTS_15, 0);
        PlayerScore playerTwoScore = new PlayerScore(0, 0, Point.POINTS_0, 0);
        MatchScore score = new MatchScore(playerOneScore, playerTwoScore);

        MatchScore result = matchScoreCalculationService.updateMatchPoint(score, PlayerType.PLAYER_ONE);

        PlayerScore playerOneScoreExpected = new PlayerScore(0, 0, Point.POINTS_30, 0);
        PlayerScore playerTwoScoreExpected = new PlayerScore(0, 0, Point.POINTS_0, 0);
        MatchScore expected = new MatchScore(playerOneScoreExpected, playerTwoScoreExpected);

        assertThat(result).isEqualTo(expected);
    }

    @Test
    void whenPlayerTwoScoresTwice_thenScoreIsLove30() {
        PlayerScore playerOneScore = new PlayerScore(0, 0, Point.POINTS_0, 0);
        PlayerScore playerTwoScore = new PlayerScore(0, 0, Point.POINTS_15, 0);
        MatchScore score = new MatchScore(playerOneScore, playerTwoScore);

        MatchScore result = matchScoreCalculationService.updateMatchPoint(score, PlayerType.PLAYER_TWO);

        PlayerScore playerOneScoreExpected = new PlayerScore(0, 0, Point.POINTS_0, 0);
        PlayerScore playerTwoScoreExpected = new PlayerScore(0, 0, Point.POINTS_30, 0);
        MatchScore expected = new MatchScore(playerOneScoreExpected, playerTwoScoreExpected);

        assertThat(result).isEqualTo(expected);
    }

    @Test
    void whenPlayerOneScoresThrice_thenScoreIs40Love() {
        PlayerScore playerOneScore = new PlayerScore(0, 0, Point.POINTS_30, 0);
        PlayerScore playerTwoScore = new PlayerScore(0, 0, Point.POINTS_0, 0);
        MatchScore score = new MatchScore(playerOneScore, playerTwoScore);

        MatchScore result = matchScoreCalculationService.updateMatchPoint(score, PlayerType.PLAYER_ONE);

        PlayerScore playerOneScoreExpected = new PlayerScore(0, 0, Point.POINTS_40, 0);
        PlayerScore playerTwoScoreExpected = new PlayerScore(0, 0, Point.POINTS_0, 0);
        MatchScore expected = new MatchScore(playerOneScoreExpected, playerTwoScoreExpected);

        assertThat(result).isEqualTo(expected);
    }

    @Test
    void whenPlayerTwoScoresThrice_thenScoreIsLove40() {
        PlayerScore playerOneScore = new PlayerScore(0, 0, Point.POINTS_0, 0);
        PlayerScore playerTwoScore = new PlayerScore(0, 0, Point.POINTS_30, 0);
        MatchScore score = new MatchScore(playerOneScore, playerTwoScore);

        MatchScore result = matchScoreCalculationService.updateMatchPoint(score, PlayerType.PLAYER_TWO);

        PlayerScore playerOneScoreExpected = new PlayerScore(0, 0, Point.POINTS_0, 0);
        PlayerScore playerTwoScoreExpected = new PlayerScore(0, 0, Point.POINTS_40, 0);
        MatchScore expected = new MatchScore(playerOneScoreExpected, playerTwoScoreExpected);

        assertThat(result).isEqualTo(expected);
    }

    @Test
    void whenBothPlayersScoreThreeTimes_thenScoreIsDeuce() {
        PlayerScore playerOneScore = new PlayerScore(0, 0, Point.POINTS_40, 0);
        PlayerScore playerTwoScore = new PlayerScore(0, 0, Point.POINTS_30, 0);
        MatchScore score = new MatchScore(playerOneScore, playerTwoScore);

        MatchScore result = matchScoreCalculationService.updateMatchPoint(score, PlayerType.PLAYER_TWO);

        PlayerScore playerOneScoreExpected = new PlayerScore(0, 0, Point.POINTS_40, 0);
        PlayerScore playerTwoScoreExpected = new PlayerScore(0, 0, Point.POINTS_40, 0);
        MatchScore expected = new MatchScore(playerOneScoreExpected, playerTwoScoreExpected);

        assertThat(result).isEqualTo(expected);
    }

    @Test
    void whenPlayerOneScoresAtDeuce_thenScoreIsAdvantagePlayerOne() {
        PlayerScore playerOneScore = new PlayerScore(0, 0, Point.POINTS_40, 0);
        PlayerScore playerTwoScore = new PlayerScore(0, 0, Point.POINTS_40, 0);
        MatchScore score = new MatchScore(playerOneScore, playerTwoScore);

        MatchScore result = matchScoreCalculationService.updateMatchPoint(score, PlayerType.PLAYER_ONE);

        PlayerScore playerOneScoreExpected = new PlayerScore(0, 0, Point.POINTS_AD, 0);
        PlayerScore playerTwoScoreExpected = new PlayerScore(0, 0, Point.POINTS_40, 0);
        MatchScore expected = new MatchScore(playerOneScoreExpected, playerTwoScoreExpected);

        assertThat(result).isEqualTo(expected);
    }

    @Test
    void whenPlayerTwoScoresAtDeuce_thenScoreIsAdvantagePlayerTwo() {
        PlayerScore playerOneScore = new PlayerScore(0, 0, Point.POINTS_40, 0);
        PlayerScore playerTwoScore = new PlayerScore(0, 0, Point.POINTS_40, 0);
        MatchScore score = new MatchScore(playerOneScore, playerTwoScore);

        MatchScore result = matchScoreCalculationService.updateMatchPoint(score, PlayerType.PLAYER_TWO);

        PlayerScore playerOneScoreExpected = new PlayerScore(0, 0, Point.POINTS_40, 0);
        PlayerScore playerTwoScoreExpected = new PlayerScore(0, 0, Point.POINTS_AD, 0);
        MatchScore expected = new MatchScore(playerOneScoreExpected, playerTwoScoreExpected);

        assertThat(result).isEqualTo(expected);
    }

    @Test
    void whenPlayerOneScoresAfterAdvantage_thenPlayerOneWins() {
        PlayerScore playerOneScore = new PlayerScore(0, 0, Point.POINTS_AD, 0);
        PlayerScore playerTwoScore = new PlayerScore(0, 0, Point.POINTS_40, 0);
        MatchScore score = new MatchScore(playerOneScore, playerTwoScore);

        MatchScore result = matchScoreCalculationService.updateMatchPoint(score, PlayerType.PLAYER_ONE);

        PlayerScore playerOneScoreExpected = new PlayerScore(0, 1, Point.POINTS_0, 0);
        PlayerScore playerTwoScoreExpected = new PlayerScore(0, 0, Point.POINTS_0, 0);
        MatchScore expected = new MatchScore(playerOneScoreExpected, playerTwoScoreExpected);

        assertThat(result).isEqualTo(expected);
    }

    @Test
    void whenPlayerTwoScoresAfterAdvantage_thenPlayerTwoWins() {
        PlayerScore playerOneScore = new PlayerScore(0, 0, Point.POINTS_40, 0);
        PlayerScore playerTwoScore = new PlayerScore(0, 0, Point.POINTS_AD, 0);
        MatchScore score = new MatchScore(playerOneScore, playerTwoScore);

        MatchScore result = matchScoreCalculationService.updateMatchPoint(score, PlayerType.PLAYER_TWO);

        PlayerScore playerOneScoreExpected = new PlayerScore(0, 0, Point.POINTS_0, 0);
        PlayerScore playerTwoScoreExpected = new PlayerScore(0, 1, Point.POINTS_0, 0);
        MatchScore expected = new MatchScore(playerOneScoreExpected, playerTwoScoreExpected);

        assertThat(result).isEqualTo(expected);
    }

    @Test
    void whenPlayerOneLosesAdvantage_thenScoreIsDeuce() {
        PlayerScore playerOneScore = new PlayerScore(0, 0, Point.POINTS_AD, 0);
        PlayerScore playerTwoScore = new PlayerScore(0, 0, Point.POINTS_40, 0);
        MatchScore score = new MatchScore(playerOneScore, playerTwoScore);

        MatchScore result = matchScoreCalculationService.updateMatchPoint(score, PlayerType.PLAYER_TWO);

        PlayerScore playerOneScoreExpected = new PlayerScore(0, 0, Point.POINTS_40, 0);
        PlayerScore playerTwoScoreExpected = new PlayerScore(0, 0, Point.POINTS_40, 0);
        MatchScore expected = new MatchScore(playerOneScoreExpected, playerTwoScoreExpected);

        assertThat(result).isEqualTo(expected);
    }

    @Test
    void whenPlayerTwoLosesAdvantage_thenScoreIsDeuce() {
        PlayerScore playerOneScore = new PlayerScore(0, 0, Point.POINTS_40, 0);
        PlayerScore playerTwoScore = new PlayerScore(0, 0, Point.POINTS_AD, 0);
        MatchScore score = new MatchScore(playerOneScore, playerTwoScore);

        MatchScore result = matchScoreCalculationService.updateMatchPoint(score, PlayerType.PLAYER_ONE);

        PlayerScore playerOneScoreExpected = new PlayerScore(0, 0, Point.POINTS_40, 0);
        PlayerScore playerTwoScoreExpected = new PlayerScore(0, 0, Point.POINTS_40, 0);
        MatchScore expected = new MatchScore(playerOneScoreExpected, playerTwoScoreExpected);

        assertThat(result).isEqualTo(expected);
    }


    @Test
    void whenPlayerOneScoresFourTimesWithoutDeuce_thenPlayerOneWins() {
        PlayerScore playerOneScore = new PlayerScore(0, 0, Point.POINTS_40, 0);
        PlayerScore playerTwoScore = new PlayerScore(0, 0, Point.POINTS_30, 0);
        MatchScore score = new MatchScore(playerOneScore, playerTwoScore);

        MatchScore result = matchScoreCalculationService.updateMatchPoint(score, PlayerType.PLAYER_ONE);

        PlayerScore playerOneScoreExpected = new PlayerScore(0, 1, Point.POINTS_0, 0);
        PlayerScore playerTwoScoreExpected = new PlayerScore(0, 0, Point.POINTS_0, 0);
        MatchScore expected = new MatchScore(playerOneScoreExpected, playerTwoScoreExpected);

        assertThat(result).isEqualTo(expected);
    }

    @Test
    void whenPlayerTwoScoresFourTimesWithoutDeuce_thenPlayerTwoWins() {
        PlayerScore playerOneScore = new PlayerScore(0, 0, Point.POINTS_15, 0);
        PlayerScore playerTwoScore = new PlayerScore(0, 0, Point.POINTS_40, 0);
        MatchScore score = new MatchScore(playerOneScore, playerTwoScore);

        MatchScore result = matchScoreCalculationService.updateMatchPoint(score, PlayerType.PLAYER_TWO);

        PlayerScore playerOneScoreExpected = new PlayerScore(0, 0, Point.POINTS_0, 0);
        PlayerScore playerTwoScoreExpected = new PlayerScore(0, 1, Point.POINTS_0, 0);
        MatchScore expected = new MatchScore(playerOneScoreExpected, playerTwoScoreExpected);

        assertThat(result).isEqualTo(expected);
    }

    @Test
    void whenPlayerOneWinsFirstSet6To4_thenScoreIs1SetToZero() {
        PlayerScore playerOneScore = new PlayerScore(0, 5, Point.POINTS_40, 0);
        PlayerScore playerTwoScore = new PlayerScore(0, 4, Point.POINTS_15, 0);
        MatchScore score = new MatchScore(playerOneScore, playerTwoScore);

        MatchScore result = matchScoreCalculationService.updateMatchPoint(score, PlayerType.PLAYER_ONE);

        PlayerScore playerOneScoreExpected = new PlayerScore(1, 0, Point.POINTS_0, 0);
        PlayerScore playerTwoScoreExpected = new PlayerScore(0, 0, Point.POINTS_0, 0);
        MatchScore expected = new MatchScore(playerOneScoreExpected, playerTwoScoreExpected);

        assertThat(result).isEqualTo(expected);
    }

    @Test
    void whenPlayerTwoWinsFirstSet6To3_thenScoreIs0SetsTo1() {
        PlayerScore playerOneScore = new PlayerScore(0, 3, Point.POINTS_40, 0);
        PlayerScore playerTwoScore = new PlayerScore(0, 5, Point.POINTS_AD, 0);
        MatchScore score = new MatchScore(playerOneScore, playerTwoScore);

        MatchScore result = matchScoreCalculationService.updateMatchPoint(score, PlayerType.PLAYER_TWO);

        PlayerScore playerOneScoreExpected = new PlayerScore(0, 0, Point.POINTS_0, 0);
        PlayerScore playerTwoScoreExpected = new PlayerScore(1, 0, Point.POINTS_0, 0);
        MatchScore expected = new MatchScore(playerOneScoreExpected, playerTwoScoreExpected);

        assertThat(result).isEqualTo(expected);
    }

    @Test
    void whenPlayerOneWinsFirstSetInTiebreak_thenScoreIs1SetToZero() {
        PlayerScore playerOneScore = new PlayerScore(0, 6, Point.POINTS_0, 6);
        PlayerScore playerTwoScore = new PlayerScore(0, 6, Point.POINTS_0, 5);
        MatchScore score = new MatchScore(playerOneScore, playerTwoScore);

        MatchScore result = matchScoreCalculationService.updateMatchPoint(score, PlayerType.PLAYER_ONE);

        PlayerScore playerOneScoreExpected = new PlayerScore(1, 0, Point.POINTS_0, 0);
        PlayerScore playerTwoScoreExpected = new PlayerScore(0, 0, Point.POINTS_0, 0);
        MatchScore expected = new MatchScore(playerOneScoreExpected, playerTwoScoreExpected);

        assertThat(result).isEqualTo(expected);
    }

    @Test
    void whenPlayerTwoWinsFirstSetInTiebreak_thenScoreIs0SetsTo1() {
        PlayerScore playerOneScore = new PlayerScore(0, 6, Point.POINTS_0, 6);
        PlayerScore playerTwoScore = new PlayerScore(0, 6, Point.POINTS_0, 7);
        MatchScore score = new MatchScore(playerOneScore, playerTwoScore);

        MatchScore result = matchScoreCalculationService.updateMatchPoint(score, PlayerType.PLAYER_TWO);

        PlayerScore playerOneScoreExpected = new PlayerScore(0, 0, Point.POINTS_0, 0);
        PlayerScore playerTwoScoreExpected = new PlayerScore(1, 0, Point.POINTS_0, 0);
        MatchScore expected = new MatchScore(playerOneScoreExpected, playerTwoScoreExpected);

        assertThat(result).isEqualTo(expected);
    }

    @Test
    void whenPlayerOneWinsTwoSetsWithoutTiebreak_thenPlayerOneWinsMatch() {
        PlayerScore playerOneScore = new PlayerScore(1, 5, Point.POINTS_40, 0);
        PlayerScore playerTwoScore = new PlayerScore(0, 4, Point.POINTS_0, 0);
        MatchScore score = new MatchScore(playerOneScore, playerTwoScore);

        MatchScore result = matchScoreCalculationService.updateMatchPoint(score, PlayerType.PLAYER_ONE);

        PlayerScore playerOneScoreExpected = new PlayerScore(2, 0, Point.POINTS_0, 0);
        PlayerScore playerTwoScoreExpected = new PlayerScore(0, 0, Point.POINTS_0, 0);
        MatchScore expected = new MatchScore(playerOneScoreExpected, playerTwoScoreExpected);

        assertThat(result).isEqualTo(expected);

        boolean matchOver = matchScoreCalculationService.isMatchOver(expected);
        assertThat(matchOver).isTrue();
    }

    @Test
    void whenPlayerTwoWinsTwoSetsWithoutTiebreak_thenPlayerTwoWinsMatch() {
        PlayerScore playerOneScore = new PlayerScore(0, 3, Point.POINTS_40, 0);
        PlayerScore playerTwoScore = new PlayerScore(1, 5, Point.POINTS_AD, 0);
        MatchScore score = new MatchScore(playerOneScore, playerTwoScore);

        MatchScore result = matchScoreCalculationService.updateMatchPoint(score, PlayerType.PLAYER_TWO);

        PlayerScore playerOneScoreExpected = new PlayerScore(0, 0, Point.POINTS_0, 0);
        PlayerScore playerTwoScoreExpected = new PlayerScore(2, 0, Point.POINTS_0, 0);
        MatchScore expected = new MatchScore(playerOneScoreExpected, playerTwoScoreExpected);

        assertThat(result).isEqualTo(expected);

        boolean matchOver = matchScoreCalculationService.isMatchOver(expected);
        assertThat(matchOver).isTrue();
    }

    @Test
    void whenMatchGoesToFinalSetTiebreakAndPlayerOneWins_thenPlayerOneWinsMatch() {
        PlayerScore playerOneScore = new PlayerScore(1, 6, Point.POINTS_0, 7);
        PlayerScore playerTwoScore = new PlayerScore(1, 6, Point.POINTS_0, 6);
        MatchScore score = new MatchScore(playerOneScore, playerTwoScore);

        MatchScore result = matchScoreCalculationService.updateMatchPoint(score, PlayerType.PLAYER_ONE);

        PlayerScore playerOneScoreExpected = new PlayerScore(2, 0, Point.POINTS_0, 0);
        PlayerScore playerTwoScoreExpected = new PlayerScore(1, 0, Point.POINTS_0, 0);
        MatchScore expected = new MatchScore(playerOneScoreExpected, playerTwoScoreExpected);

        assertThat(result).isEqualTo(expected);

        boolean matchOver = matchScoreCalculationService.isMatchOver(expected);
        assertThat(matchOver).isTrue();
    }

    @Test
    void whenMatchGoesToFinalSetTiebreakAndPlayerTwoWins_thenPlayerTwoWinsMatch() {
        PlayerScore playerOneScore = new PlayerScore(1, 6, Point.POINTS_0, 3);
        PlayerScore playerTwoScore = new PlayerScore(1, 6, Point.POINTS_0, 6);
        MatchScore score = new MatchScore(playerOneScore, playerTwoScore);

        MatchScore result = matchScoreCalculationService.updateMatchPoint(score, PlayerType.PLAYER_TWO);

        PlayerScore playerOneScoreExpected = new PlayerScore(1, 0, Point.POINTS_0, 0);
        PlayerScore playerTwoScoreExpected = new PlayerScore(2, 0, Point.POINTS_0, 0);
        MatchScore expected = new MatchScore(playerOneScoreExpected, playerTwoScoreExpected);

        assertThat(result).isEqualTo(expected);

        boolean matchOver = matchScoreCalculationService.isMatchOver(expected);
        assertThat(matchOver).isTrue();
    }

    @Test
    void whenPlayerOneWinsTiebreak7To5_thenPlayerOneWinsSet() {
        PlayerScore playerOneScore = new PlayerScore(0, 6, Point.POINTS_0, 6);
        PlayerScore playerTwoScore = new PlayerScore(1, 6, Point.POINTS_0, 5);
        MatchScore score = new MatchScore(playerOneScore, playerTwoScore);

        MatchScore result = matchScoreCalculationService.updateMatchPoint(score, PlayerType.PLAYER_ONE);

        PlayerScore playerOneScoreExpected = new PlayerScore(1, 0, Point.POINTS_0, 0);
        PlayerScore playerTwoScoreExpected = new PlayerScore(1, 0, Point.POINTS_0, 0);
        MatchScore expected = new MatchScore(playerOneScoreExpected, playerTwoScoreExpected);

        assertThat(result).isEqualTo(expected);
    }

    @Test
    void whenPlayerTwoWinsTiebreak7To3_thenPlayerTwoWinsSet() {
        PlayerScore playerOneScore = new PlayerScore(0, 6, Point.POINTS_0, 3);
        PlayerScore playerTwoScore = new PlayerScore(1, 6, Point.POINTS_0, 6);
        MatchScore score = new MatchScore(playerOneScore, playerTwoScore);

        MatchScore result = matchScoreCalculationService.updateMatchPoint(score, PlayerType.PLAYER_TWO);

        PlayerScore playerOneScoreExpected = new PlayerScore(0, 0, Point.POINTS_0, 0);
        PlayerScore playerTwoScoreExpected = new PlayerScore(2, 0, Point.POINTS_0, 0);
        MatchScore expected = new MatchScore(playerOneScoreExpected, playerTwoScoreExpected);

        assertThat(result).isEqualTo(expected);
    }

    @Test
    void whenTiebreakContinuesAfter6AllAndPlayerOneWins9To7_thenPlayerOneWinsSet() {
        PlayerScore playerOneScore = new PlayerScore(0, 6, Point.POINTS_0, 8);
        PlayerScore playerTwoScore = new PlayerScore(0, 6, Point.POINTS_0, 7);
        MatchScore score = new MatchScore(playerOneScore, playerTwoScore);

        MatchScore result = matchScoreCalculationService.updateMatchPoint(score, PlayerType.PLAYER_ONE);

        PlayerScore playerOneScoreExpected = new PlayerScore(1, 0, Point.POINTS_0, 0);
        PlayerScore playerTwoScoreExpected = new PlayerScore(0, 0, Point.POINTS_0, 0);
        MatchScore expected = new MatchScore(playerOneScoreExpected, playerTwoScoreExpected);

        assertThat(result).isEqualTo(expected);
    }

    @Test
    void whenTiebreakContinuesAfter6AllAndPlayerTwoWins10To8_thenPlayerTwoWinsSet() {
        PlayerScore playerOneScore = new PlayerScore(0, 6, Point.POINTS_0, 8);
        PlayerScore playerTwoScore = new PlayerScore(0, 6, Point.POINTS_0, 9);
        MatchScore score = new MatchScore(playerOneScore, playerTwoScore);

        MatchScore result = matchScoreCalculationService.updateMatchPoint(score, PlayerType.PLAYER_TWO);

        PlayerScore playerOneScoreExpected = new PlayerScore(0, 0, Point.POINTS_0, 0);
        PlayerScore playerTwoScoreExpected = new PlayerScore(1, 0, Point.POINTS_0, 0);
        MatchScore expected = new MatchScore(playerOneScoreExpected, playerTwoScoreExpected);

        assertThat(result).isEqualTo(expected);
    }

    @Test
    void whenBothPlayersWinOneSetEachAndPlayerOneWinsFinalSet_thenPlayerOneWinsMatch() {
        PlayerScore playerOneScore = new PlayerScore(1, 6, Point.POINTS_40, 0);
        PlayerScore playerTwoScore = new PlayerScore(1, 5, Point.POINTS_0, 0);
        MatchScore score = new MatchScore(playerOneScore, playerTwoScore);

        MatchScore result = matchScoreCalculationService.updateMatchPoint(score, PlayerType.PLAYER_ONE);

        PlayerScore playerOneScoreExpected = new PlayerScore(2, 0, Point.POINTS_0, 0);
        PlayerScore playerTwoScoreExpected = new PlayerScore(1, 0, Point.POINTS_0, 0);
        MatchScore expected = new MatchScore(playerOneScoreExpected, playerTwoScoreExpected);

        assertThat(result).isEqualTo(expected);

        boolean matchOver = matchScoreCalculationService.isMatchOver(expected);
        assertThat(matchOver).isTrue();
    }

    @Test
    void whenBothPlayersWinOneSetEachAndPlayerTwoWinsFinalSet_thenPlayerTwoWinsMatch() {
        PlayerScore playerOneScore = new PlayerScore(1, 4, Point.POINTS_40, 0);
        PlayerScore playerTwoScore = new PlayerScore(1, 6, Point.POINTS_AD, 0);
        MatchScore score = new MatchScore(playerOneScore, playerTwoScore);

        MatchScore result = matchScoreCalculationService.updateMatchPoint(score, PlayerType.PLAYER_TWO);

        PlayerScore playerOneScoreExpected = new PlayerScore(1, 0, Point.POINTS_0, 0);
        PlayerScore playerTwoScoreExpected = new PlayerScore(2, 0, Point.POINTS_0, 0);
        MatchScore expected = new MatchScore(playerOneScoreExpected, playerTwoScoreExpected);

        assertThat(result).isEqualTo(expected);

        boolean matchOver = matchScoreCalculationService.isMatchOver(expected);
        assertThat(matchOver).isTrue();
    }

    @Test
    void whenTryingToScoreAfterMatchIsOver_thenNoChange() {
        PlayerScore playerOneScore = new PlayerScore(2, 0, Point.POINTS_0, 0);
        PlayerScore playerTwoScore = new PlayerScore(1, 0, Point.POINTS_0, 0);
        MatchScore score = new MatchScore(playerOneScore, playerTwoScore);

        MatchScore result = matchScoreCalculationService.updateMatchPoint(score, PlayerType.PLAYER_TWO);

        PlayerScore playerOneScoreExpected = new PlayerScore(2, 0, Point.POINTS_0, 0);
        PlayerScore playerTwoScoreExpected = new PlayerScore(1, 0, Point.POINTS_0, 0);
        MatchScore expected = new MatchScore(playerOneScoreExpected, playerTwoScoreExpected);

        assertThat(result).isEqualTo(expected);

        boolean matchOver = matchScoreCalculationService.isMatchOver(expected);
        assertThat(matchOver).isTrue();
    }
}