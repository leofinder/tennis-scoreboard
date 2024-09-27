package com.craftelix.mapper;

import com.craftelix.entity.type.PlayerType;
import org.mapstruct.Mapper;
import org.mapstruct.ValueMapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PlayerTypeMapper {

    PlayerTypeMapper INSTANCE = Mappers.getMapper(PlayerTypeMapper.class);

    @ValueMapping(target = "PLAYER_ONE", source = "player-one")
    @ValueMapping(target = "PLAYER_TWO", source = "player-two")
    PlayerType toEntity(String playerType);
}
