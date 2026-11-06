package co.ceiba.gestionpersonas.domain;

import jakarta.persistence.*;
import java.time.LocalDate;
    
@Entity
@Table(name = "personas")
public class Persona {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", length  = 100)
    private String nombre;

    @Column(nullable = false, length = 100)
    private String apellido;

    @Column (nullable = false, unique = true, length = 150)
    private String email;

    @Column(name = "fecha_nacimiento")
    private LocalDate fechaNacimiento;

    public Persona() {

    }

    public Persona(Long id, String nombre, String apellido, String email, LocalDate fechaNacimiento) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.fechaNacimiento = fechaNacimiento;
    }

    public Long getId() { 
        return id;
    }
    public void setId(Long id) {
        this.id = id;
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

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }
    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

} 