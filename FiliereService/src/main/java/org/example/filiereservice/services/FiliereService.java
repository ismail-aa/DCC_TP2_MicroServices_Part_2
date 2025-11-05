package org.example.filiereservice.services;

import org.example.filiereservice.dto.RequestFiliereDto;
import org.example.filiereservice.dto.ResponseFiliereDto;
import java.util.List;

public interface FiliereService {
    ResponseFiliereDto Add_Filiere(RequestFiliereDto requestFiliereDto);
    List<ResponseFiliereDto> GetAllFilieres();
    ResponseFiliereDto GetFiliereById(Integer id);
    ResponseFiliereDto Update_Filiere(Integer id, RequestFiliereDto requestFiliereDto);
    void DeleteFiliereById(Integer id);
}