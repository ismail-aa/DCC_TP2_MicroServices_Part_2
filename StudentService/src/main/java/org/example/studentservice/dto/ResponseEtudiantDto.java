package org.example.studentservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseEtudiantDto {

    private Integer idEtudiant;
    private String nom;
    private String prenom;
    private String cne;
    private Integer idFiliere;
    private Filiere filiere;

}