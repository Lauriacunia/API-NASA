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



}