package org.example.filiereservice.mapper;

import org.example.filiereservice.dto.RequestFiliereDto;
import org.example.filiereservice.dto.ResponseFiliereDto;
import org.example.filiereservice.entities.Filiere;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class FiliereMapper {

    public Filiere DTO_to_Entity(RequestFiliereDto requestFiliereDto) {
        Filiere filiere = new Filiere();
        BeanUtils.copyProperties(requestFiliereDto, filiere);
        return filiere;
    }

    public ResponseFiliereDto Entity_to_DTO(Filiere filiere) {
        ResponseFiliereDto responseFiliereDto = new ResponseFiliereDto();
        BeanUtils.copyProperties(filiere, responseFiliereDto);
        return responseFiliereDto;
    }
}