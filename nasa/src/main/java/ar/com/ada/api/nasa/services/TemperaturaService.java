package ar.com.ada.api.nasa.services;

import java.math.BigDecimal;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.ada.api.nasa.entities.*;
import ar.com.ada.api.nasa.models.*;
import ar.com.ada.api.nasa.repos.*;

@Service
public class TemperaturaService {

    @Autowired
    TemperaturaRepository temperaturaRepository;


    public void crearTemperatura(Temperatura temperatura){

        temperaturaRepository.save(temperatura);
    }


    public Temperatura buscarTemperaturaPorId(int temperaturaId){
    
        Optional<Temperatura> pOptional = temperaturaRepository.findById(temperaturaId);
       
        if(pOptional.isPresent()){

            return pOptional.get();
        }
        return null;
        

    }

    public List<Temperatura> buscarTemperaturasPorAnio(int anio){

        return temperaturaRepository.findAllByAnio(anio);

    }
    public void borrarTemperatura(Temperatura temperatura){

        temperatura.setAnioTemperatura(0);

    }
}