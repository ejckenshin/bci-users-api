package cl.smart.job.exercise.bci.service.mapper;

import cl.smart.job.exercise.bci.controller.data.PhoneRequest;
import cl.smart.job.exercise.bci.repository.entity.PhoneEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PhoneMapper {

    @Mapping(target = "creationDate", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "state", constant = "A")
    PhoneEntity phoneRequestToPhoneEntity(PhoneRequest request);
}
