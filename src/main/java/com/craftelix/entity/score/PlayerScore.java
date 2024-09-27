package com.craftelix.entity.score;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlayerScore {

    private int set;

    private int game;

    private Point point = Point.POINTS_0;

    private int tiebreak;
}
