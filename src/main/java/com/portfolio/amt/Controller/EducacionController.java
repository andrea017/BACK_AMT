package com.portfolio.amt.Controller;

import com.portfolio.amt.Dto.dtoEducacion;
import com.portfolio.amt.Entity.Educacion;
import com.portfolio.amt.Security.Controller.Mensaje;
import com.portfolio.amt.Service.EducacionService;
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
@RequestMapping("/educacion")
@CrossOrigin(origins = {"https://frontamt.web.app", "http://localhost:4200"})

public class EducacionController {

    @Autowired
    EducacionService sEducacion;

    @GetMapping("/lista")
    public ResponseEntity<List<Educacion>> list() {
        List<Educacion> list = sEducacion.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<Educacion> getbyId(@PathVariable("id") int id) {
        if (!sEducacion.existsById(id)) {
            return new ResponseEntity(new Mensaje("No existe el ID"), HttpStatus.BAD_REQUEST);
        }

        Educacion educacion = sEducacion.getOne(id).get();
        return new ResponseEntity(educacion, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        if (!sEducacion.existsById(id)) {
            return new ResponseEntity(new Mensaje("No existe el ID"), HttpStatus.NOT_FOUND);
        }

        sEducacion.delete(id);
        return new ResponseEntity(new Mensaje("Educación eliminada"), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody dtoEducacion dtoeducacion) {
        if (StringUtils.isBlank(dtoeducacion.getNombreE())) {
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        //if (sEducacion.existsByNombreE(dtoeducacion.getNombreE())) {
           // return new ResponseEntity(new Mensaje("Ese nombre ya existe"), HttpStatus.BAD_REQUEST);
        //}

        Educacion educacion = new Educacion(
        dtoeducacion.getNombreE(), dtoeducacion.getDescripcionE(), dtoeducacion.getFechaE(),
                dtoeducacion.getLogoE(), dtoeducacion.getTareasE()
        );
        sEducacion.save(educacion);
        return new ResponseEntity(new Mensaje("Educacion creada"), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody dtoEducacion dtoeducacion) {
        if (!sEducacion.existsById(id)) {
            return new ResponseEntity(new Mensaje("No existe el ID"), HttpStatus.NOT_FOUND);
        }
        //if (sEducacion.existsByNombreE(dtoeducacion.getNombreE()) && sEducacion.getByNombreE(dtoeducacion.getNombreE()).get().getId() != id) {
            //return new ResponseEntity(new Mensaje("Ese nombre ya existe"), HttpStatus.BAD_REQUEST);
        //}

        if (StringUtils.isBlank(dtoeducacion.getNombreE())) {
            return new ResponseEntity(new Mensaje("El campo no puede estar vacío"), HttpStatus.BAD_REQUEST);
        }

        Educacion educacion = sEducacion.getOne(id).get();

        educacion.setNombreE(dtoeducacion.getNombreE());
        educacion.setDescripcionE(dtoeducacion.getDescripcionE());
        educacion.setFechaE(dtoeducacion.getFechaE());
        educacion.setLogoE(dtoeducacion.getLogoE());
        educacion.setTareasE(dtoeducacion.getTareasE());

        sEducacion.save(educacion);

        return new ResponseEntity(new Mensaje("Educación actualizada"), HttpStatus.OK);
    }

}
