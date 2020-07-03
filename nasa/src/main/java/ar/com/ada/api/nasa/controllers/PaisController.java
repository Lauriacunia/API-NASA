package ar.com.ada.api.nasa.controllers;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ar.com.ada.api.nasa.entities.*;
import ar.com.ada.api.nasa.models.requests.PaisRequest;
import ar.com.ada.api.nasa.models.responses.GenericResponse;
import ar.com.ada.api.nasa.services.*;

@RestController
public class PaisController {
    @Autowired
    PaisService paisService;


    @GetMapping("/paises")
    public ResponseEntity<List<Pais>> listarPaises() {
        
        return ResponseEntity.ok(paisService.listarPaises());
 
    }

    @GetMapping("/paises/{id}")
    public ResponseEntity<?> buscarPaisPorId(@PathVariable int id){

        Pais pais = paisService.buscarPaisPorId(id);

    
        if (pais != null) {
            return ResponseEntity.ok(pais);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @PutMapping("/empleados/{id}/nombre")
    public ResponseEntity<?> actualizarNombrePais(@PathVariable int id, @RequestBody String nuevoNombre){

        Pais paisOriginal = paisService.buscarPaisPorId(id);

        if (paisOriginal != null) {

            paisService.actualizarNombrePais(paisOriginal, nuevoNombre);

            GenericResponse resp = new GenericResponse();
            resp.isOk = true;
            resp.id = paisOriginal.getPaisId();
            resp.message = "Se ha actualizado con exito";
    
            return ResponseEntity.ok(resp);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /** POST /paises : que permita la creación de un país */
    
    @PostMapping("/paises")
    public ResponseEntity<?> crearPais(@RequestBody PaisRequest info){
        
        Pais pais = new Pais();
        pais.setPaisId(info.paisId);
        pais.setNombre(info.nombre); 
        
        paisService.crearPais(pais);
        
        GenericResponse resp = new GenericResponse();
        resp.isOk = true;
        resp.id = pais.getPaisId();
        resp.message = " País generado con exito";

        return ResponseEntity.ok(resp);
    
    }
}