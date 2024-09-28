package com.craftelix.mapper;

import com.craftelix.dto.FinishedMatchRequestDto;
import com.craftelix.dto.FinishedMatchResponseDto;
import com.craftelix.entity.Match;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = PlayerMapper.class)
public interface FinishedMatchMapper {

    FinishedMatchMapper INSTANCE = Mappers.getMapper(FinishedMatchMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "score", ignore = true)
    Match toEntity(FinishedMatchRequestDto finishedMatchRequestDto);

    FinishedMatchResponseDto toDto(Match match);
}
