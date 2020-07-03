package ar.com.ada.api.rrhh.controllers;

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

import ar.com.ada.api.nasa.entities;
import ar.com.ada.api.nasa.entities.Temperatura;
import ar.com.ada.api.nasa.models.requests.EmpleadoRequest;
import ar.com.ada.api.nasa.models.requests.SueldoInfoRequest;
import ar.com.ada.api.nasa.models.responses.GenericResponse;
import ar.com.ada.api.nasa.services.PaisService;
import ar.com.ada.api.nasa.services.TemperaturaService;

@RestController
public class TemperaturaController {

    @Autowired
    PaisService paisService;
    @Autowired
    TemperaturaService temperaturaService;

    @PostMapping("/temperaturas")
    public ResponseEntity<?> crearTemperatura(@RequestBody TemperaturaRequest info){
        
        Temperatura temperatura = new Temperatura();
        temperatura.setPais(paisService.buscarPaisPorId(paisId);
        temperatura.setAnioTemperatura(info.anioTemperatura);
        temperatura.setTemperaturaGrados(info.temperaturaGrados);
        

        temperaturaService.crearTemperatura(temperatura);
        
        GenericResponse resp = new GenericResponse();
        resp.isOk = true;
        resp.id = temperatura.getTemperaturaId();
        resp.message = " generado con exito";

        return ResponseEntity.ok(resp);

    }

    @GetMapping("/empleados")
    public ResponseEntity<?> listarEmpleado(){

        return ResponseEntity.ok(empleadoService.listarEmpleados());
    }




    @DeleteMapping("/empleados/{id}")
    public ResponseEntity<?> borrarEmpleado(@PathVariable int id){

        Empleado empleado = empleadoService.traerEmpledoPorId(id);

        if(empleado != null){

            empleadoService.borrarEmpleado(empleado);

            GenericResponse resp = new GenericResponse();
            resp.isOk = true;
            resp.id = empleado.getEmpleadoId();
            resp.message = "Fue eliminado con exito";

       return ResponseEntity.ok(resp); 
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        

    }
}  
