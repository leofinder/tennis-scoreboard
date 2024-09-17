package com.craftelix.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlayerScoreResponseDto {

    private int set;

    private int game;

    private String point;

    private int tiebreak;
}
