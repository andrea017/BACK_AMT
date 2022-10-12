
package com.portfolio.amt.Dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


public class dtoProyectos {
    @NotBlank
    String nombre;
    @NotBlank
    @Size(min = 1 , max = 1000, message ="no cumple con la longitud")
    String descripcion;
    @NotBlank
    String img;

    public dtoProyectos() {
    }

    public dtoProyectos(String nombre, String descripcion, String img) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.img = img;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
    
    
    
}
