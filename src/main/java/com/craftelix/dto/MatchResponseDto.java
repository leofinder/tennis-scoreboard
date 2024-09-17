package com.craftelix.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MatchResponseDto {

    private PlayerResponseDto playerOne;

    private PlayerResponseDto playerTwo;

    private PlayerResponseDto winner;

    private MatchScoreResponseDto score;
}
