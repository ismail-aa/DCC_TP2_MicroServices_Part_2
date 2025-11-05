package org.example.studentservice.services;

import org.example.studentservice.FeignClient.FiliereClient;
import org.example.studentservice.dto.Filiere;
import org.example.studentservice.dto.RequestEtudiantDto;
import org.example.studentservice.dto.ResponseEtudiantDto;
import org.example.studentservice.entities.Etudiant;
import org.example.studentservice.mapper.EtudiantMapper;
import org.example.studentservice.repository.EtudiantRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class EtudiantServiceImpl implements EtudiantService {

    private FiliereClient filiereRestClient;
    private EtudiantRepository etudiantRepository;
    private EtudiantMapper etudiantMapper;

    public EtudiantServiceImpl(FiliereClient filiereRestClient, EtudiantRepository etudiantRepository, EtudiantMapper etudiantMapper) {
        this.filiereRestClient = filiereRestClient;
        this.etudiantRepository = etudiantRepository;
        this.etudiantMapper = etudiantMapper;
    }

    @Override
    public ResponseEtudiantDto Add_Etudiant(RequestEtudiantDto requestEtudiantDto) {
        // Vérifier si la filière existe avant de sauvegarder
        try {
            Filiere filiere = filiereRestClient.getFiliereById(requestEtudiantDto.getIdFiliere());


            if (filiere == null) {
                throw new RuntimeException("Filière introuvable !");
            }

        } catch (Exception e) {
            throw new RuntimeException("Filière introuvable : id = " + requestEtudiantDto.getIdFiliere());
        }

        // Si la filière existe, on sauvegarde l'étudiant
        Etudiant etudiant = etudiantMapper.DTO_to_Entity(requestEtudiantDto);
        Etudiant saved_etudiant = etudiantRepository.save(etudiant);
        saved_etudiant.setFiliere(filiereRestClient.getFiliereById(saved_etudiant.getIdFiliere()));
        return etudiantMapper.Entity_to_DTO(saved_etudiant);
    }

    @Override
    public List<ResponseEtudiantDto> GetAllEtudiants() {
        List<Etudiant> etudiants = etudiantRepository.findAll();
        List<ResponseEtudiantDto> etudiantDtos = new ArrayList<>();

        for (Etudiant e : etudiants) {
            try {
                //récuperer filiere de chaque etudiant
                Filiere filiere = filiereRestClient.getFiliereById(e.getIdFiliere());
                e.setFiliere(filiere);

            } catch (Exception ex) {
                // cas probleme
                System.err.println("Filiere pas existe " + e.getIdFiliere());
                e.setFiliere(null);
            }

            ResponseEtudiantDto dto = etudiantMapper.Entity_to_DTO(e);
            etudiantDtos.add(dto);
        }

        return etudiantDtos;
    }

    @Override
    public ResponseEtudiantDto GetEtudiantById(Integer idEtudiant) {
        Etudiant etudiant = etudiantRepository.findById(idEtudiant).orElse(null);

        if (etudiant == null) return null;

        // si etudiant existe on récuperer filiere.
        Filiere filiere = filiereRestClient.getFiliereById(etudiant.getIdFiliere());
        etudiant.setFiliere(filiere);

        return etudiantMapper.Entity_to_DTO(etudiant);
    }

    @Override
    public ResponseEtudiantDto Update_Etudiant(Integer idEtudiant, RequestEtudiantDto requestEtudiantDto) {
        Etudiant new_etudiant = etudiantMapper.DTO_to_Entity(requestEtudiantDto);

        Etudiant etudiant = etudiantRepository.findById(idEtudiant).orElse(null);

        if (new_etudiant.getNom() != null) etudiant.setNom(new_etudiant.getNom());
        if (new_etudiant.getPrenom() != null) etudiant.setPrenom(new_etudiant.getPrenom());
        if (new_etudiant.getCne() != null) etudiant.setCne(new_etudiant.getCne());
        if (new_etudiant.getIdFiliere() != null) etudiant.setIdFiliere(new_etudiant.getIdFiliere());

        Etudiant saved_etudiant = etudiantRepository.save(etudiant);
        return etudiantMapper.Entity_to_DTO(saved_etudiant);
    }

    @Override
    public void DeleteEtudiantById(Integer id) {
        etudiantRepository.deleteById(id);
    }
}