package com.craftelix.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FinishedMatchResponseDto {

    private Long id;

    private PlayerResponseDto playerOne;

    private PlayerResponseDto playerTwo;

    private PlayerResponseDto winner;

}
