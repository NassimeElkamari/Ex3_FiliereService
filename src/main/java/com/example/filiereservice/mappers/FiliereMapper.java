package com.example.filiereservice.mappers;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import com.example.filiereservice.dto.RequestDtoFiliere;
import com.example.filiereservice.dto.ResponseDtoFiliere;
import com.example.filiereservice.entitie.Filiere;

@Component
public class FiliereMapper {
    public Filiere toEntity(RequestDtoFiliere dto) {
        Filiere f = new Filiere();
        BeanUtils.copyProperties(dto,f);
        return f;
    }
    public ResponseDtoFiliere toDto(Filiere f) {
        ResponseDtoFiliere dto = new ResponseDtoFiliere();
        BeanUtils.copyProperties(f,dto);
        return dto;
    }
}