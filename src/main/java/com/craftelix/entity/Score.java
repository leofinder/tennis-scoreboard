package com.craftelix.entity;

import lombok.Data;

@Data
public class Score {

    private byte pointPlayer1;
    private byte pointPlayer2;
    private byte tieBreakPlayer1;
    private byte tieBreakPlayer2;
    private byte gamePlayer1;
    private byte gamePlayer2;
    private byte setPlayer1;
    private byte setPlayer2;
}
