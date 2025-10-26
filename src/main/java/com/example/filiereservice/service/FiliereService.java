package com.example.filiereservice.service;

import java.util.List;
import com.example.filiereservice.dto.RequestDtoFiliere;
import com.example.filiereservice.dto.ResponseDtoFiliere;

public interface FiliereService {
    ResponseDtoFiliere save(RequestDtoFiliere dto);
    List<ResponseDtoFiliere> findAll();
    ResponseDtoFiliere findById(Long id);
    ResponseDtoFiliere update(Long id, RequestDtoFiliere dto);
    void delete(Long id);
}