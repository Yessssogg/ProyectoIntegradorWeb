package modelo;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelo.Horario;
import modelo.Turno;
import modelo.Usuario;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2024-12-03T16:43:29")
@StaticMetamodel(Doctor.class)
public class Doctor_ extends Persona_ {

    public static volatile SingularAttribute<Doctor, Usuario> unUsuario;
    public static volatile SingularAttribute<Doctor, String> especialidad;
    public static volatile ListAttribute<Doctor, Turno> listaTurnos;
    public static volatile SingularAttribute<Doctor, Horario> unHorario;

}