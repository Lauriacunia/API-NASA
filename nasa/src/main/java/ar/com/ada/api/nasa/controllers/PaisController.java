package ar.com.ada.api.nasa.controllers;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ar.com.ada.api.nasa.entities;
import ar.com.ada.api.nasa.services;

@RestController
public class PaisController {
    @Autowired
    PaisService paisService;


    @GetMapping("/paises")
    public ResponseEntity<List<Paises>> listarPaises() {
        
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

    
}