package com.craftelix.mapper;

import com.craftelix.dto.PlayerRequestDto;
import com.craftelix.dto.PlayerResponseDto;
import com.craftelix.entity.Player;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PlayerMapper {

    PlayerMapper INSTANCE = Mappers.getMapper(PlayerMapper.class);

    PlayerResponseDto toDto(Player player);

    @Mapping(target = "id", ignore = true)
    Player toEntity(PlayerRequestDto playerRequestDto);
}
