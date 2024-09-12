package com.craftelix.entity;

import lombok.Data;

@Data
public class MatchScore {

    private byte setPlayer1;
    private byte setPlayer2;
    private byte gamePlayer1;
    private byte gamePlayer2;
    private Point pointPlayer1;
    private Point pointPlayer2;
}
