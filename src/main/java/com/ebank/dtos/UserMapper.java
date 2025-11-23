package com.ebank.dtos;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.ebank.entity.UserEntity;

@Mapper(componentModel = "spring")
public interface UserMapper {
	
	/**
	 * 
	 * @param request
	 * @return
	 * 
	 * Mapper Record to Entity 
	 * 
	 */
	UserEntity toEntity(UserRequest request);
	
	
	/**
	 * 
	 * @param entity
	 * @return
	 * 
	 * Mapper from Entity to Response
	 */
	UserResponse toResponse(UserEntity entity);
	
	/**
	 * 
	 * @param request
	 * @param entity
	 * 
	 * Updating Entity using records
	 */
	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	void updateEntityFromRecord(UserRequest request,@MappingTarget UserEntity entity);
	
	

}
