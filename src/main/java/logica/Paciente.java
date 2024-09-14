
package logica;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Paciente extends Persona implements Serializable {
    //    private int id_paciente;
    private boolean tiene_OS;
    private String tipo_Sangre;
    @OneToOne
    private Responsable unResponsable;
    @OneToMany(mappedBy="pacien")
    private List<Turno> listaTurnos;
    @OneToOne
    private Historia historia;   
    public Paciente() {
    }
  
    public Paciente(boolean tiene_OS, String tipo_Sangre, Responsable unResponsable, 
            List<Turno> listaTurnos, Historia historia, int id, String dni, String nombre, 
            String apellido, String telefono, String direccion, Date fecha_nac) {
        super(id, dni, nombre, apellido, telefono, direccion, fecha_nac);
        this.tiene_OS = tiene_OS;
        this.tipo_Sangre = tipo_Sangre;
        this.unResponsable = unResponsable;
        this.listaTurnos = listaTurnos;
        this.historia = historia;
    }

    public boolean isTiene_OS() {
        return tiene_OS;
    }

    public void setTiene_OS(boolean tiene_OS) {
        this.tiene_OS = tiene_OS;
    }

    public String getTipo_Sangre() {
        return tipo_Sangre;
    }

    public void setTipo_Sangre(String tipo_Sangre) {
        this.tipo_Sangre = tipo_Sangre;
    }

    public Responsable getUnResponsable() {
        return unResponsable;
    }

    public void setUnResponsable(Responsable unResponsable) {
        this.unResponsable = unResponsable;
    }

    public List<Turno> getListaTurnos() {
        return listaTurnos;
    }

    public void setListaTurnos(List<Turno> listaTurnos) {
        this.listaTurnos = listaTurnos;
    }

    public Historia getHistoria() {
        return historia;
    }

    public void setHistoria(Historia historia) {
        this.historia = historia;
    }

}
