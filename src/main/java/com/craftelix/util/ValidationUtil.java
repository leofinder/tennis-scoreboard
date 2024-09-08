package com.craftelix.util;

import com.craftelix.dto.PlayerRequestDto;
import com.craftelix.exception.InvalidParameterException;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ValidationUtil {

    public static void validatePlayers(PlayerRequestDto player1, PlayerRequestDto player2) {
        String playerName1 = player1.getName();
        String playerName2 = player2.getName();

        if (playerName1 == null || playerName1.isBlank()) {
            throw new InvalidParameterException("Player 1 name cannot be blank");
        }

        if (playerName2 == null || playerName2.isBlank()) {
            throw new InvalidParameterException("Player 2 name cannot be blank");
        }

        if (playerName1.equals(playerName2)) {
            throw new InvalidParameterException("Player names cannot be the same");
        }
    }
}
