package com.craftelix.mapper;

import com.craftelix.dto.MatchScoreResponseDto;
import com.craftelix.entity.score.MatchScore;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = PlayerScoreMapper.class)
public interface MatchScoreMapper {

    MatchScoreMapper INSTANCE = Mappers.getMapper(MatchScoreMapper.class);

    MatchScoreResponseDto toDto(MatchScore matchScore);
}
