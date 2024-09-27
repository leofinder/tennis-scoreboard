package com.craftelix.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MatchScoreResponseDto {

    private PlayerScoreResponseDto playerOneScore;

    private PlayerScoreResponseDto playerTwoScore;

    private boolean tiebreak;
}
