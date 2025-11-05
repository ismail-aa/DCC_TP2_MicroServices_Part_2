package org.example.studentservice.mapper;

import org.example.studentservice.dto.RequestEtudiantDto;
import org.example.studentservice.dto.ResponseEtudiantDto;
import org.example.studentservice.entities.Etudiant;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class EtudiantMapper {

    public Etudiant DTO_to_Entity(RequestEtudiantDto requestEtudiantDto) {
        Etudiant etudiant = new Etudiant();
        BeanUtils.copyProperties(requestEtudiantDto, etudiant);
        return etudiant;
    }

    public ResponseEtudiantDto Entity_to_DTO(Etudiant etudiant) {
        ResponseEtudiantDto responseEtudiantDto = new ResponseEtudiantDto();
        BeanUtils.copyProperties(etudiant, responseEtudiantDto);
        return responseEtudiantDto;
    }
}