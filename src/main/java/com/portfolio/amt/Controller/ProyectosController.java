
package com.portfolio.amt.Controller;

import com.portfolio.amt.Dto.dtoProyectos;
import com.portfolio.amt.Entity.Proyectos;
import com.portfolio.amt.Security.Controller.Mensaje;
import com.portfolio.amt.Service.ProyectosService;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/proyectos")
@CrossOrigin(origins = {"https://frontamt.web.app", "http://localhost:4200"})
public class ProyectosController {
    @Autowired
    ProyectosService proyectosS;

    @GetMapping("/lista")
    public ResponseEntity<List<Proyectos>> list() {
        List<Proyectos> list = proyectosS.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody dtoProyectos dtopro) {
        if (StringUtils.isBlank(dtopro.getNombre())) {
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if (proyectosS.existsByNombre(dtopro.getNombre())) {
            return new ResponseEntity(new Mensaje("Esa experiencia existe"), HttpStatus.BAD_REQUEST);
        }

        Proyectos proyecto = new Proyectos(dtopro.getNombre(), dtopro.getDescripcion(), dtopro.getImg());
        proyectosS.save(proyecto);

        return new ResponseEntity(new Mensaje("Proyectos agregada"), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody dtoProyectos dtopro) {
        //Validamos si existe el id
        if (!proyectosS.existsById(id))
            return new ResponseEntity(new Mensaje("El id no existe"), HttpStatus.BAD_REQUEST);
            //Compara nombre de experiencias     
        if (proyectosS.existsByNombre(dtopro.getNombre()) && proyectosS.getByNombre(dtopro.getNombre()).get().getId() != id)
            return new ResponseEntity(new Mensaje("Este proyecto ya existe"), HttpStatus.BAD_REQUEST);
            
            //No puede estar vacio
        if (StringUtils.isBlank(dtopro.getNombre())) 
            return new ResponseEntity(new Mensaje("El nombre es obligatiorio"), HttpStatus.BAD_REQUEST);
            

            Proyectos proyecto = proyectosS.getOne(id).get();
            proyecto.setNombre(dtopro.getNombre());
            proyecto.setDescripcion(dtopro.getDescripcion());
            proyecto.setImg(dtopro.getImg());

            proyectosS.save(proyecto);
            return new ResponseEntity(new Mensaje("Proyectos actualizados"), HttpStatus.OK);
        }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id){
    //Validamos si existe el id
        if (!proyectosS.existsById(id))
            return new ResponseEntity(new Mensaje("El id no existe"), HttpStatus.BAD_REQUEST);
        
        proyectosS.delete(id);
        
        return new ResponseEntity(new Mensaje ("Proyecto eliminado"), HttpStatus.OK);
    }
       
    @GetMapping("/detail/{id}")
    public ResponseEntity<Proyectos> getById(@PathVariable("id") int id){
        if(!proyectosS.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        Proyectos proyecto = proyectosS.getOne(id).get();
        return new ResponseEntity(proyecto, HttpStatus.OK);
    }
    
}
