package org.example.studentservice.services;

import org.example.studentservice.dto.RequestEtudiantDto;
import org.example.studentservice.dto.ResponseEtudiantDto;

import java.util.List;

public interface EtudiantService {

    ResponseEtudiantDto Add_Etudiant(RequestEtudiantDto requestEtudiantDto);
    List<ResponseEtudiantDto> GetAllEtudiants();
    ResponseEtudiantDto GetEtudiantById(Integer idEtudiant);
    ResponseEtudiantDto Update_Etudiant(Integer idEtudiant, RequestEtudiantDto requestEtudiantDto);
    void DeleteEtudiantById(Integer idEtudiant);
}