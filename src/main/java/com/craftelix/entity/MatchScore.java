package com.craftelix.entity;

import lombok.*;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class MatchScore {

    @NonNull
    private PlayerScore playerOneScore;
    @NonNull
    private PlayerScore playerTwoScore;

    private boolean tiebreak = false;
}
