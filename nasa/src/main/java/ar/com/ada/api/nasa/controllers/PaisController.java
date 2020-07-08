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

/**GET /paises : que devuelva la lista de países SIN las temperaturas. */

    @GetMapping("/paises")
    public ResponseEntity<List<Pais>> listarPaises() {
        
        return ResponseEntity.ok(paisService.listarPaises());
 
    }

/**GET /paises/{id} : que devuelva la info de un pais en particular(SIN las temperaturas) */

    @GetMapping("/paises/{id}")
    public ResponseEntity<?> buscarPaisPorId(@PathVariable int id){

        Pais pais = paisService.buscarPaisPorId(id);

    
        if (pais != null) {
            return ResponseEntity.ok(pais);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

/*PUT /paises/{id} : que actualice solo el nombre del país. Usar un requestBody que solo tenga el
nombre del país.*/

    @PutMapping("/empleados/{id}/nombre")
    public ResponseEntity<GenericResponse> actualizarNombrePais(@PathVariable int id, @RequestBody String nuevoNombre){

        GenericResponse r = new GenericResponse();
        Pais paisOriginal = paisService.buscarPaisPorId(id);

        if (paisOriginal != null) {

            paisService.actualizarNombrePais(paisOriginal, nuevoNombre);

           
            r.isOk = true;
            
            r.message = "Se ha actualizado el nombre del pais con exito";
    
            return ResponseEntity.ok(r);
            
        } else {

            r.isOk = false;
            r.message = "No se pudo actualizar el pais.";

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