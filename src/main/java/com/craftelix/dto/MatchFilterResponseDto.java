package com.craftelix.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MatchFilterResponseDto {

    private int page;

    private int pageCount;

    private String playerName;

    private List<FinishedMatchResponseDto> matches;
}
