package logica;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import logica.Horario;
import logica.Turno;
import logica.Usuario;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2024-09-13T22:49:26")
@StaticMetamodel(Doctor.class)
public class Doctor_ extends Persona_ {

    public static volatile SingularAttribute<Doctor, Usuario> unUsuario;
    public static volatile SingularAttribute<Doctor, String> especialidad;
    public static volatile ListAttribute<Doctor, Turno> listaTurnos;
    public static volatile SingularAttribute<Doctor, Horario> unHorario;

}