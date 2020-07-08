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
    PaisRepository paisRepository;


    public List<Pais> listarPaises() {

        return paisRepository.findAll();
    }



    public Pais buscarPaisPorId(int paisId){
    
        Optional<Pais> pOptional = paisRepository.findById(paisId);
       
        if(pOptional.isPresent()){

            return pOptional.get();
        }
        return null;
        

    }
    public void actualizarNombrePais(Pais paisOriginal, String nuevoNombre){

        paisOriginal.setNombre(nuevoNombre);

        paisRepository.save(paisOriginal);

    }
    public void crearPais(Pais pais){

        paisRepository.save(pais);
    }
}