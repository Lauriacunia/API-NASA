package ar.com.ada.api.nasa.services;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.ada.api.nasa.entities;

import ar.com.ada.api.rrhh.repos.PaisRepository;

@Service
public class PaisService {

    @Autowired
    PaisRepository repository;

    public void crearCategoria(Categoria categoria) {

        repository.save(categoria);

    }

    public List<Paises> listarPaises() {

        return repository.findAll();
    }

    public List<Empleado> traerEmpleadosPorCategoria(int categoriaId){

        Optional<Categoria> cOptional = repository.findById(categoriaId);
        List<Empleado> listaVacia = new ArrayList<>();
        
        if(cOptional.isPresent()){

            return (cOptional.get()).getEmpleados();
        }
        return listaVacia;

    }

    public Pais buscarPaisPorId(int paisId){
    
        Optional<Pais> pOptional = repository.findById(paisId);
       
        if(cOptional.isPresent()){

            return cOptional.get();
        }
        return null;
        

    }

}