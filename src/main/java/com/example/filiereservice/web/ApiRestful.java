package com.example.filiereservice.web;

import com.example.filiereservice.dto.RequestDtoFiliere;
import com.example.filiereservice.dto.ResponseDtoFiliere;
import com.example.filiereservice.service.FiliereService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/filieres")
@RequiredArgsConstructor
public class ApiRestful {

    private final FiliereService service;

    @Operation(
            summary = "Créer une nouvelle filière",
            description = "Ajoute une nouvelle filière dans la base de données",
            requestBody = @RequestBody(
                    required = true,
                    description = "Données de la filière à créer",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = RequestDtoFiliere.class)
                    )
            ),
            responses = {
                    @ApiResponse(responseCode = "201", description = "Filière créée avec succès",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ResponseDtoFiliere.class))),
                    @ApiResponse(responseCode = "400", description = "Requête invalide")
            }
    )
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    @PostMapping
    public ResponseEntity<ResponseDtoFiliere> create(@org.springframework.web.bind.annotation.RequestBody RequestDtoFiliere dto) {
        return new ResponseEntity<>(service.save(dto), HttpStatus.CREATED);
    }

    @Operation(
            summary = "Lister toutes les filières",
            description = "Retourne la liste complète des filières enregistrées",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Liste récupérée avec succès",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ResponseDtoFiliere.class)))
            }
    )
    @PreAuthorize("hasAuthority('SCOPE_USER')")
    @GetMapping
    public List<ResponseDtoFiliere> all() {
        return service.findAll();
    }

    @Operation(
            summary = "Obtenir une filière par ID",
            description = "Retourne une filière spécifique en fonction de son identifiant",
            parameters = {
                    @Parameter(name = "id", description = "Identifiant unique de la filière", required = true)
            },
            responses = {
                    @ApiResponse(responseCode = "200", description = "Filière trouvée",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ResponseDtoFiliere.class))),
                    @ApiResponse(responseCode = "404", description = "Filière non trouvée")
            }
    )
    @PreAuthorize("hasAuthority('SCOPE_USER')")
    @GetMapping("/{id}")
    public ResponseEntity<ResponseDtoFiliere> byId(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @Operation(
            summary = "Mettre à jour une filière existante",
            description = "Modifie le code ou le libellé d'une filière existante",
            parameters = {
                    @Parameter(name = "id", description = "Identifiant de la filière à mettre à jour", required = true)
            },
            requestBody = @RequestBody(
                    required = true,
                    description = "Données de la filière à mettre à jour",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = RequestDtoFiliere.class))
            ),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Filière mise à jour avec succès",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ResponseDtoFiliere.class))),
                    @ApiResponse(responseCode = "404", description = "Filière non trouvée")
            }
    )
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<ResponseDtoFiliere> update(
            @PathVariable Long id,
            @org.springframework.web.bind.annotation.RequestBody RequestDtoFiliere dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @Operation(
            summary = "Supprimer une filière",
            description = "Supprime définitivement une filière par son identifiant",
            parameters = {
                    @Parameter(name = "id", description = "Identifiant de la filière à supprimer", required = true)
            },
            responses = {
                    @ApiResponse(responseCode = "204", description = "Filière supprimée avec succès"),
                    @ApiResponse(responseCode = "404", description = "Filière non trouvée")
            }
    )
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
