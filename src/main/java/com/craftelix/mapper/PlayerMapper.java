package com.craftelix.mapper;

import com.craftelix.dto.PlayerRequestDto;
import com.craftelix.dto.PlayerResponseDto;
import com.craftelix.entity.Player;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PlayerMapper {

    PlayerMapper INSTANCE = Mappers.getMapper(PlayerMapper.class);

    PlayerResponseDto toDto(Player player);

    Player toEntity(PlayerRequestDto playerRequestDto);
}
