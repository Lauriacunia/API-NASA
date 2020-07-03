package ar.com.ada.api.nasa.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ar.com.ada.api.nasa.entities.*;

@Repository
public interface TemperaturaRepository extends JpaRepository <Temperatura,Integer> {

    //con query nativo

    @Query(value = "select p.nombre, t.temperatura_grados  from temperatura t  inner join pais p  on t.pais_id = p.pais_id   where año_temperatura = ?", nativeQuery = true)
	List<Temperatura> findAllByAnio(@Param("año_Temperatura") int anio);
    

  
}