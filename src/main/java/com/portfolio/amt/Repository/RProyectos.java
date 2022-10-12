
package com.portfolio.amt.Repository;

import com.portfolio.amt.Entity.Proyectos;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RProyectos extends JpaRepository<Proyectos, Integer>{
    Optional <Proyectos> findByNombre(String nombre);
    public boolean existsByNombre(String nombre);
}
