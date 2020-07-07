package ar.com.ada.api.nasa.entities;

import java.util.List;

import javax.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "temperatura")
public class Temperatura {

  @Id
  @Column(name = "temperatura_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int temperaturaId;

  @ManyToOne
  @JoinColumn(name = "pais_id", referencedColumnName = "pais_id") // aca se pone la columan de la FK
  private Pais pais;

  @Column(name= "año_temperatura")
  private int anioTemperatura;

  @Column(name= "temperatura_grados")
  private double temperaturaGrados;

public int getTemperaturaId() {
	return temperaturaId;
}

public void setTemperaturaId(int temperaturaId) {
	this.temperaturaId = temperaturaId;
}



public int getAnioTemperatura() {
	return anioTemperatura;
}

public void setAnioTemperatura(int anioTemperatura) {
	this.anioTemperatura = anioTemperatura;
}

public double getTemperaturaGrados() {
	return temperaturaGrados;
}

public void setTemperaturaGrados(double temperaturaGrados) {
	this.temperaturaGrados = temperaturaGrados;
}

public Pais getPais() {
  return pais;
}

public void setPais(Pais pais) {
  this.pais = pais;
}



  
}