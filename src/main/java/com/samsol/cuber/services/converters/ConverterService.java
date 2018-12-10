package com.samsol.cuber.services.converters;

import java.util.List;

import static java.util.stream.Collectors.toList;

public interface ConverterService<Entity, Dto> {
    Dto convertToDto(Entity entity);

    Entity convertToEntity(Dto dto);

    default List<Dto> convertToDtoList(final List<Entity> entityList) {
        return entityList.stream().map(this::convertToDto).collect(toList());
    }

    default List<Entity> convertToEntityList(final List<Dto> dtoList) {
        return dtoList.stream().map(this::convertToEntity).collect(toList());
    }


}
