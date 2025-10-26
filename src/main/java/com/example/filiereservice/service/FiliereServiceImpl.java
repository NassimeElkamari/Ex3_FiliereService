package com.example.filiereservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import com.example.filiereservice.dto.RequestDtoFiliere;
import com.example.filiereservice.dto.ResponseDtoFiliere;
import com.example.filiereservice.entitie.Filiere;
import com.example.filiereservice.mappers.FiliereMapper;
import com.example.filiereservice.repository.FiliereRepository;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
@Transactional
public class FiliereServiceImpl implements FiliereService {
    private final FiliereRepository repository;
    private final FiliereMapper mapper;

    @Override
    public ResponseDtoFiliere save(RequestDtoFiliere dto) {
        Filiere f = mapper.toEntity(dto);
        return mapper.toDto(repository.save(f));
    }

    @Override
    public List<ResponseDtoFiliere> findAll() {
        return repository.findAll().stream().map(mapper::toDto).collect(Collectors.toList());
    }

    @Override
    public ResponseDtoFiliere findById(Long id) {
        return repository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Filière " + id + " introuvable"
                        )
                );
    }



    @Override
    public ResponseDtoFiliere update(Long id, RequestDtoFiliere req) {
        Filiere f = repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Filiere non trouvée"));
        if (req.getCode() != null) f.setCode(req.getCode());
        if (req.getLibelle() != null) f.setLibelle(req.getLibelle());
        return mapper.toDto(repository.save(f));
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}