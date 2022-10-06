
package com.portfolio.amt.Repository;

import com.portfolio.amt.Entity.HyS;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RHyS extends JpaRepository<HyS, Integer>{
Optional <HyS> findByNombre(String nombre);
public boolean existsByNombre(String nombre);
    
}
