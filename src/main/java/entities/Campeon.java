/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author yorsh
 */
@Entity(name="campeones")
@Table(name = "Campeones")
@NamedQueries({
    @NamedQuery(name = "Campeones.findAll", query = "SELECT c FROM campeones c"),
    @NamedQuery(name = "Campeones.findByNombre", query = "SELECT c FROM campeones c WHERE c.nombre = :nombre"),
    @NamedQuery(name = "Campeones.findByCoste", query = "SELECT c FROM campeones c WHERE c.coste = :coste"),
    @NamedQuery(name = "Campeones.findByOrigen", query = "SELECT c FROM campeones c WHERE c.origen = :origen"),
    @NamedQuery(name = "Campeones.findByClase1", query = "SELECT c FROM campeones c WHERE c.clase1 = :clase1"),
    @NamedQuery(name = "Campeones.findByClase2", query = "SELECT c FROM campeones c WHERE c.clase2 = :clase2")})
public class Campeon implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "coste")
    private int coste;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "origen")
    private String origen;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "clase1")
    private String clase1;
    @Size(max = 40)
    @Column(name = "clase2")
    private String clase2;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "nombre")
    private String nombre;

    public Campeon() {
    }

    public Campeon(String nombre) {
        this.nombre = nombre;
    }

    public Campeon(String nombre, int coste, String origen, String clase1) {
        this.nombre = nombre;
        this.coste = coste;
        this.origen = origen;
        this.clase1 = clase1;
    }

    public Campeon(int coste, String origen, String clase1, String clase2, String nombre) {
        this.coste = coste;
        this.origen = origen;
        this.clase1 = clase1;
        this.clase2 = clase2;
        this.nombre = nombre;
    }
    
    

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nombre != null ? nombre.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Campeon)) {
            return false;
        }
        Campeon other = (Campeon) object;
        if ((this.nombre == null && other.nombre != null) || (this.nombre != null && !this.nombre.equals(other.nombre))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.evaluaciondos.Campeones[ nombre=" + nombre + " ]";
    }

    public int getCoste() {
        return coste;
    }

    public void setCoste(int coste) {
        this.coste = coste;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getClase1() {
        return clase1;
    }

    public void setClase1(String clase1) {
        this.clase1 = clase1;
    }

    public String getClase2() {
        return clase2;
    }

    public void setClase2(String clase2) {
        this.clase2 = clase2;
    }
    
}
