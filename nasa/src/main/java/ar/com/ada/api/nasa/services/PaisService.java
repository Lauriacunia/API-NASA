package ar.com.ada.api.nasa.services;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.config.RepositoryConfiguration;
import org.springframework.stereotype.Service;

import ar.com.ada.api.nasa.entities.*;

import ar.com.ada.api.nasa.repos.*;

@Service
public class PaisService {

    @Autowired
    PaisRepository repository;


    public List<Pais> listarPaises() {

        return repository.findAll();
    }



    public Pais buscarPaisPorId(int paisId){
    
        Optional<Pais> pOptional = repository.findById(paisId);
       
        if(pOptional.isPresent()){

            return pOptional.get();
        }
        return null;
        

    }
    public void actualizarNombrePais(Pais paisOriginal, String nuevoNombre){

        paisOriginal.setNombre(nuevoNombre);

        repository.save(paisOriginal);

    }
    
}