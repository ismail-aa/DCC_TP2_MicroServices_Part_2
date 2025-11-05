package org.example.filiereservice.services;

import org.example.filiereservice.dto.RequestFiliereDto;
import org.example.filiereservice.dto.ResponseFiliereDto;
import org.example.filiereservice.entities.Filiere;
import org.example.filiereservice.mapper.FiliereMapper;
import org.example.filiereservice.repository.FiliereRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FiliereServiceImpl implements FiliereService {

    private FiliereRepository filiereRepository;
    private FiliereMapper filiereMapper;

    public FiliereServiceImpl(FiliereRepository filiereRepository, FiliereMapper filiereMapper) {
        this.filiereRepository = filiereRepository;
        this.filiereMapper = filiereMapper;
    }

    @Override
    public ResponseFiliereDto Add_Filiere(RequestFiliereDto requestFiliereDto) {
        Filiere filiere = filiereMapper.DTO_to_Entity(requestFiliereDto);
        Filiere saved_filiere = filiereRepository.save(filiere);
        return filiereMapper.Entity_to_DTO(saved_filiere);
    }

    @Override
    public List<ResponseFiliereDto> GetAllFilieres() {
        List<Filiere> filieres = filiereRepository.findAll();
        List<ResponseFiliereDto> resoponseFiliereDtos = new ArrayList<>();

        for (Filiere f : filieres) {
            resoponseFiliereDtos.add(filiereMapper.Entity_to_DTO(f));
        }

        return resoponseFiliereDtos;
    }

    @Override
    public ResponseFiliereDto GetFiliereById(Integer id) {
        Filiere filiere = filiereRepository.findById(id).orElseThrow();
        return filiereMapper.Entity_to_DTO(filiere);
    }

    @Override
    public ResponseFiliereDto Update_Filiere(Integer id, RequestFiliereDto requestFiliereDto) {
        Filiere new_filiere = filiereMapper.DTO_to_Entity(requestFiliereDto);
        Filiere filiere = filiereRepository.findById(id).orElseThrow();

        if (new_filiere.getCode() != null) filiere.setCode(new_filiere.getCode());
        if (new_filiere.getLibelle() != null) filiere.setLibelle(new_filiere.getLibelle());

        Filiere saved_filiere = filiereRepository.save(filiere);
        return filiereMapper.Entity_to_DTO(saved_filiere);
    }

    @Override
    public void DeleteFiliereById(Integer id) {
        filiereRepository.deleteById(id);
    }
}