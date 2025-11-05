package org.example.studentservice.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.studentservice.dto.Filiere;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Etudiant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEtudiant;

    private String nom;
    private String prenom;
    private String cne;
    //need to add it then work with openfeign and resttemplate
    private Integer idFiliere;

    //@OneToMany(mappedBy ="filiere") n'est dans la meme DB
    @Transient //l'attribue n'est pas représenter dans la DB ----> n'est persistant.
    private Filiere filiere;
}