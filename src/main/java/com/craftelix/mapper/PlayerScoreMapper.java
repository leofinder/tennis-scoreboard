package com.craftelix.mapper;

import com.craftelix.dto.PlayerScoreResponseDto;
import com.craftelix.entity.PlayerScore;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = PointMapper.class)
public interface PlayerScoreMapper {

    PlayerScoreMapper INSTANCE = Mappers.getMapper(PlayerScoreMapper.class);

    PlayerScoreResponseDto toDto(PlayerScore playerScore);
}
