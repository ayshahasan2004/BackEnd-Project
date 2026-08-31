package com.cafe.cafeBackend.model;

import org.hibernate.cache.spi.support.AbstractReadWriteAccess;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ItemMapper {

    MenuItem toResponse(AbstractReadWriteAccess.Item item);
}
