package mx.com.invested.crud.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * @author Alexis Novella Vidal
 * Entity que contiene el mapeo de la tabla empleado
 *
 */
@Entity
@Table(name = "empleado")
public class Empleado {

    private long id;
    private String primerNombre;
    private String segundoNombre;
    private String apPaterno;
    private String apMaterno;
    private String email;

    public Empleado() {

    }

    public Empleado(String primerNombre, String segundoNombre, String apPaterno, String apMaterno, String email) {
        this.primerNombre = primerNombre;
        this.segundoNombre = segundoNombre;
        this.apPaterno = apPaterno;
        this.apMaterno = apMaterno;
        this.email = email;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "primer_nombre", nullable = false)
    public String getPrimerNombre() {
        return primerNombre;
    }
    public void setPrimerNombre(String primerNombre) {
        this.primerNombre = primerNombre;
    }

    @Column(name = "segundo_nombre", nullable = true)
    public String getSegundoNombre() {
        return segundoNombre;
    }
    public void setSegundoNombre(String segundoNombre) {
        this.segundoNombre = segundoNombre;
    }
    
    @Column(name = "apellido_paterno", nullable = false)
    public String getApPaterno() {
        return apPaterno;
    }
    public void setApPaterno(String apPaterno) {
        this.apPaterno = apPaterno;
    }
    
    @Column(name = "apellido_materno", nullable = false)
    public String getApMaterno() {
        return apMaterno;
    }
    public void setApMaterno(String apMaterno) {
        this.apMaterno = apMaterno;
    }

    @Column(name = "email", nullable = false)
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

}