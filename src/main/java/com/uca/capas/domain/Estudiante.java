package com.uca.capas.domain;
import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;


@Entity
@Table(schema = "public", name = "estudiante")
public class Estudiante {
    @Id
    @Column(name = "id_estudiante")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigo;
    
    @NotEmpty(message = "El campo nombre no puede estar vacio")
    @Column(name = "nombre")
    private String nombre;
    
    @NotEmpty(message = "El campo apellido no puede estar vacio")
    @Column(name = "apellido")
    private String apellido;

    @Min(value = 18, message = "No puede ser menor a 18 anios")
    @Column(name = "edad")
    private int edad;

    @Column(name = "estado")
    private Boolean estado;
    
    public Estudiante(){}

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getEdad() { return edad; }

    public void setEdad(int edad) { this.edad = edad; }

    public Boolean getEstado() { return estado; }

    public void setEstado(Boolean estado) { this.estado = estado; }

    public String getEstadoDelegate() {
        if (this.estado == null) return "";
        else
            return estado ? "Activo" : "Inactivo";
    }
}
