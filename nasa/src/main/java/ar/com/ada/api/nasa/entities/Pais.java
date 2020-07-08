package ar.com.ada.api.nasa.entities;



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
	
    @OneToMany(mappedBy = "pais", cascade = CascadeType.ALL) //aca se pone el atributo pais de la clase temperatura
    @LazyCollection(LazyCollectionOption.FALSE)
	@JsonIgnore
    private List<Temperatura> temperaturas = new ArrayList<>();

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