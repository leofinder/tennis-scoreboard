package com.craftelix.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FinishedMatchRequestDto {

    private PlayerRequestDto playerOne;

    private PlayerRequestDto playerTwo;

    private PlayerRequestDto winner;

}
