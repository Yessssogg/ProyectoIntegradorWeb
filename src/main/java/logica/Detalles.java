
package logica;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Detalles implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_detalles;

    private LocalDate fechaAtencion;
    private String motivoConsulta;
    private String diagnostico;
    private String tratamiento;
    private String recetaMedica;     
    @ManyToOne
    @JoinColumn(name = "id_historiaclinica")
    private Historia historiaClinica;

    public Detalles() {
    }

    public Detalles(int id_detalles, LocalDate fechaAtencion, String motivoConsulta, String diagnostico, String tratamiento, 
            String recetaMedica, Historia historiaClinica) {
        this.id_detalles = id_detalles;
        this.fechaAtencion = fechaAtencion;
        this.motivoConsulta = motivoConsulta;
        this.diagnostico = diagnostico;
        this.tratamiento = tratamiento;
        this.recetaMedica = recetaMedica;
        this.historiaClinica = historiaClinica;
    }

    public int getId_detalles() {
        return id_detalles;
    }

    public void setId_detalles(int id_detalles) {
        this.id_detalles = id_detalles;
    }

    public LocalDate getFechaAtencion() {
        return fechaAtencion;
    }

    public void setFechaAtencion(LocalDate fechaAtencion) {
        this.fechaAtencion = fechaAtencion;
    }

    public String getMotivoConsulta() {
        return motivoConsulta;
    }

    public void setMotivoConsulta(String motivoConsulta) {
        this.motivoConsulta = motivoConsulta;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public String getTratamiento() {
        return tratamiento;
    }

    public void setTratamiento(String tratamiento) {
        this.tratamiento = tratamiento;
    }

    public String getRecetaMedica() {
        return recetaMedica;
    }

    public void setRecetaMedica(String recetaMedica) {
        this.recetaMedica = recetaMedica;
    }

    public Historia getHistoriaClinica() {
        return historiaClinica;
    }

    public void setHistoriaClinica(Historia historiaClinica) {
        this.historiaClinica = historiaClinica;
    }

    
}
