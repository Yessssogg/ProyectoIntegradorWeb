
package logica;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Historia implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_historia;

    private LocalDate fechaCreacion;
    private String alergias;
    private String enfermedadesPrevias;
    private String tratamientosActuales;
    private String cirugiasPrevias;
    private String observaciones; // Observaciones generales aquí
    private String estado;
    private String proximaCita;

    

    @OneToMany(mappedBy = "historiaClinica")
    private List<Detalles> detallesAtencion;

    public Historia() {
    }

    public Historia(int id_historia, LocalDate fechaCreacion, String alergias, String enfermedadesPrevias, String tratamientosActuales, 
            String cirugiasPrevias, String observaciones, String estado, String proximaCita,  List<Detalles> detallesAtencion) {
        this.id_historia = id_historia;
        this.fechaCreacion = fechaCreacion;
        this.alergias = alergias;
        this.enfermedadesPrevias = enfermedadesPrevias;
        this.tratamientosActuales = tratamientosActuales;
        this.cirugiasPrevias = cirugiasPrevias;
        this.observaciones = observaciones;
        this.estado = estado;
        this.proximaCita = proximaCita;  
        this.detallesAtencion = detallesAtencion;
    }

    public int getId_historia() {
        return id_historia;
    }

    public void setId_historia(int id_historia) {
        this.id_historia = id_historia;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getAlergias() {
        return alergias;
    }

    public void setAlergias(String alergias) {
        this.alergias = alergias;
    }

    public String getEnfermedadesPrevias() {
        return enfermedadesPrevias;
    }

    public void setEnfermedadesPrevias(String enfermedadesPrevias) {
        this.enfermedadesPrevias = enfermedadesPrevias;
    }

    public String getTratamientosActuales() {
        return tratamientosActuales;
    }

    public void setTratamientosActuales(String tratamientosActuales) {
        this.tratamientosActuales = tratamientosActuales;
    }

    public String getCirugiasPrevias() {
        return cirugiasPrevias;
    }

    public void setCirugiasPrevias(String cirugiasPrevias) {
        this.cirugiasPrevias = cirugiasPrevias;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getProximaCita() {
        return proximaCita;
    }

    public void setProximaCita(String proximaCita) {
        this.proximaCita = proximaCita;
    }
 
    public List<Detalles> getDetallesAtencion() {
        return detallesAtencion;
    }

    public void setDetallesAtencion(List<Detalles> detallesAtencion) {
        this.detallesAtencion = detallesAtencion;
    }

   
}
