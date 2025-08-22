package cl.smart.job.exercise.bci.service.mapper;

import cl.smart.job.exercise.bci.controller.data.UserRegisterRequest;
import cl.smart.job.exercise.bci.repository.entity.PhoneEntity;
import cl.smart.job.exercise.bci.repository.entity.UserEntity;
import org.hibernate.cache.spi.support.AbstractReadWriteAccess;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = {PhoneMapper.class})
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "creationDate", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "state", constant = "A")
    @Mapping(target = "name", source = "name", qualifiedByName = "trimString")
    @Mapping(target = "email", source = "email", qualifiedByName = "trimString")
    @Mapping(target = "userName", source = "email", qualifiedByName = "getUserName")
    @Mapping(target = "password", source = "password", qualifiedByName = "trimString")
    UserEntity userRegisterRequestToUserEntity(UserRegisterRequest request);

    @AfterMapping
    default void setUserEntityInPhones(@MappingTarget UserEntity userEntity) {
        if (userEntity.getPhones() != null) {
            for (PhoneEntity phoneEntity : userEntity.getPhones()) {
                phoneEntity.setUser(userEntity);
            }
        }
    }

    @Named("trimString")
    default String trimString(String valor) {
        if (valor != null) {
            return valor.trim();
        }
        return null;
    }

    @Named("getUserName")
    default String getUserName(String email) {
        if (email == null || !email.contains("@")) {
            return null;
        }
        return email.substring(0, email.indexOf('@'));
    }
}
