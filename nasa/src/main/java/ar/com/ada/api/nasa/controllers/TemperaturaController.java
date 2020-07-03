package ar.com.ada.api.nasa.controllers;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ar.com.ada.api.nasa.entities.*;
import ar.com.ada.api.nasa.models.requests.*;
import ar.com.ada.api.nasa.models.responses.GenericResponse;
import ar.com.ada.api.nasa.services.*;


@RestController
public class TemperaturaController {

    @Autowired
    PaisService paisService;
    @Autowired
    TemperaturaService temperaturaService;

    @PostMapping("/temperaturas")
    public ResponseEntity<?> crearTemperatura(@RequestBody TemperaturaRequest info){
        
        Temperatura temperatura = new Temperatura();
        temperatura.setPais(paisService.buscarPaisPorId(info.paisId));
        temperatura.setAnioTemperatura(info.anioTemperatura);
        temperatura.setTemperaturaGrados(info.temperaturaGrados);
        

        temperaturaService.crearTemperatura(temperatura);
        
        GenericResponse resp = new GenericResponse();
        resp.isOk = true;
        resp.id = temperatura.getTemperaturaId();
        resp.message = " generado con exito";

        return ResponseEntity.ok(resp);
    
    }



    @DeleteMapping("/temperaturas/{id}")
    public ResponseEntity<?> borrarTemperatura(@PathVariable int id){

        Temperatura temperatura = temperaturaService.buscarTemperaturaPorId(id);

        if(temperatura != null){

            temperaturaService.borrarTemperatura(temperatura);

            GenericResponse resp = new GenericResponse();
            resp.isOk = true;
            resp.id = temperatura.getTemperaturaId();
            resp.message = "Fue eliminada con exito";

       return ResponseEntity.ok(resp); 
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        

    }
    /*GET /temperaturas/paises/{codigoPais} : que devuelva la lista de temperaturas con sus años de un
    país especifico, indicado por “codigoPais”. */
    


}  
