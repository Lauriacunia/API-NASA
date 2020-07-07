package ar.com.ada.api.nasa.controllers;

import java.util.*;


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
import ar.com.ada.api.nasa.models.responses.TemperaturaMaxPaisResponse;
import ar.com.ada.api.nasa.services.*;


@RestController
public class TemperaturaController {

    @Autowired
    PaisService paisService;
    @Autowired
    TemperaturaService temperaturaService;

/*POST /temperaturas : que registre una temperatura de un país en un año específico */

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
        resp.message = "Registro de Temperatura generado con exito";

        return ResponseEntity.ok(resp);
    
    }


 /*   DELETE /temperaturas/{id}: no se borrará la temperatura id, deberá cambiar el año a 0. */

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
    
    @GetMapping("/temperaturas/paises/{id}")
    public ResponseEntity<?> buscarTemperaturasPorPaisPorId(@PathVariable int id){

        Pais pais = paisService.buscarPaisPorId(id);

        List<Temperatura> temperaturasPorPais = new ArrayList();
        temperaturasPorPais = pais.getTemperaturas();

        if (pais != null) {
            return ResponseEntity.ok(temperaturasPorPais);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }
/* GET /temperaturas/anios/{anio} : que devuelva la lista de temperaturas de un año en particular en
el siguiente formato JSON Array:
[{
“nombrePais”: “Argentina”,
“grados”: 29
},
{
“nombrePais”: “Venezuela”,
“grados”: 45
}]


*/

@GetMapping("/temperaturas/anio/{anio}")
public ResponseEntity<List<Temperatura>> buscarTemperaturasPorAnio(@PathVariable int anioTemperatura) {
   
    return    ResponseEntity.ok(temperaturaService.buscarTemperaturasPorAnio(anioTemperatura));

}



/*GET /temperaturas/maximas/{codigoPais} : que devuelva la temperatura máxima para un país en
particular en este formato JSON(informar el año en que ocurrió)
{
“nombrePais”: “Venezuela”,
“temperaturaMaxima”: 45,
“anio”: 2011
}*/

@GetMapping("/temperaturas/maximas/{id}")
public ResponseEntity<?> buscarTemperaturaMaximaDePaisPorId(@PathVariable int id){

    Pais pais = paisService.buscarPaisPorId(id);
    List<Temperatura> temperaturasPorPais = new ArrayList();
        temperaturasPorPais = pais.getTemperaturas();

    Temperatura temperaturaMax = temperaturaService.buscarTemperaturaMaximaDePais(temperaturasPorPais);

    TemperaturaMaxPaisResponse tm = new TemperaturaMaxPaisResponse();
    tm.nombrePais = pais.getNombre();
    tm.temperatura = temperaturaMax.getTemperaturaGrados();
    tm.anio = temperaturaMax.getAnioTemperatura();

    return ResponseEntity.ok(tm);
    
    //return new ResponseEntity<>(HttpStatus.NOT_FOUND);

}


}  
