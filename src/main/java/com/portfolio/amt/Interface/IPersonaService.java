
package com.portfolio.amt.Interface;

import com.portfolio.amt.Entity.Persona;
import java.util.List;


public interface IPersonaService {
    //traer una lista de personas
    public List <Persona> getPersona();
    
    //traer un objeto tipo Persona
    public void savePersona (Persona persona);
    
    //eliminar un objeto buscandolo por Id
    public void deletePersona (Long id);
    
    //buscar una persona
    public Persona findPersona (Long id);
    
    
    
}
