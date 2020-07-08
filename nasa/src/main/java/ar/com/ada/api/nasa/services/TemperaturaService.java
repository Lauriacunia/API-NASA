package ar.com.ada.api.nasa.services;

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

    public void crearTemperatura(int anio, int codigoPais, double grados) {

        Temperatura temperatura = new Temperatura();

        temperatura.getPais().setCodigoPais(codigoPais);
        temperatura.setAnio(anio);
        temperatura.setGrados(grados);
        
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

    // si hay mas de un valor maximo (dos años con la misma tempratura) fallaria
    
    public Temperatura buscarTemperaturaMaximaDePais(List<Temperatura> temperaturasPorPais){

        Temperatura temperaturaMax = null;

        for (Temperatura temperatura : temperaturasPorPais) {
           
            if (temperaturaMax.getTemperaturaGrados() < temperatura.getTemperaturaGrados());
            temperaturaMax = temperatura;
        }

        return temperaturaMax;

    }
    public void borrarTemperatura(Temperatura temperatura){

        temperatura.setAnioTemperatura(0);

    }
}