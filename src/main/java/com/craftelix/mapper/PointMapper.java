package com.craftelix.mapper;

import com.craftelix.entity.score.Point;
import org.mapstruct.Mapper;
import org.mapstruct.ValueMapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PointMapper {

    PointMapper INSTANCE = Mappers.getMapper(PointMapper.class);

    @ValueMapping(target = "00", source = "POINTS_0")
    @ValueMapping(target = "15", source = "POINTS_15")
    @ValueMapping(target = "30", source = "POINTS_30")
    @ValueMapping(target = "40", source = "POINTS_40")
    @ValueMapping(target = "AD", source = "POINTS_AD")
    String toDto(Point point);
}
