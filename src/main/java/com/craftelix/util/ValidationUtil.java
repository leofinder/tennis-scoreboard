package com.craftelix.util;

import com.craftelix.dto.PlayerRequestDto;
import com.craftelix.exception.InvalidParameterException;
import lombok.experimental.UtilityClass;

import java.util.UUID;

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

        if (playerName1.equalsIgnoreCase(playerName2)) {
            throw new InvalidParameterException("Player names cannot be the same");
        }
    }

    public static void validateUUID(String uuid) {
        if (uuid == null || uuid.isBlank()) {
            throw new InvalidParameterException("UUID cannot be blank");
        }

        try {
            UUID.fromString(uuid);
        } catch (IllegalArgumentException e) {
            throw new InvalidParameterException("Invalid UUID");
        }
    }

    public static void validatePlayerType(String playerTypeString) {
        if (playerTypeString == null || playerTypeString.isBlank()) {
            throw new InvalidParameterException("Invalid player type");
        }
    }

    public static void validatePage(String page) {
        if (page == null || page.isBlank()) {
            return;
        }
        if (!page.matches("^-?\\d+$")) {
            throw new InvalidParameterException("Invalid page number");
        }
    }
}
