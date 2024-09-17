package com.craftelix.mapper;

import com.craftelix.dto.MatchResponseDto;
import com.craftelix.entity.Match;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = MatchScoreMapper.class)
public interface MatchMapper {

    MatchMapper INSTANCE = Mappers.getMapper(MatchMapper.class);

    MatchResponseDto toDto(Match match);
}
