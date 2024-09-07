package com.craftelix.mapper;

import com.craftelix.dto.PlayerRequestDto;
import com.craftelix.entity.Player;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PlayerMapper {

    PlayerMapper INSTANCE = Mappers.getMapper(PlayerMapper.class);

    Player toEntity(PlayerRequestDto playerRequestDto);
}
