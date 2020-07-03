package ar.com.ada.api.nasa.entities;

import java.math.BigDecimal;

import java.util.*;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name = "pais")
public class Pais {
    @Id
    @Column(name = "pais_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int paisId;

    private String nombre;
    
    @OneToMany(mappedBy = "temperatura", cascade = CascadeType.ALL) //,fetch = FetchType.EAGER)
    @LazyCollection(LazyCollectionOption.FALSE)
    @JsonIgnore
    private List<Temperatura> temperaturas;

	public int getPaisId() {
		return paisId;
	}

	public void setPaisId(int paisId) {
		this.paisId = paisId;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Temperatura> getTemperaturas() {
		return temperaturas;
	}

	public void setTemperaturas(List<Temperatura> temperaturas) {
		this.temperaturas = temperaturas;
	}
   

    

}