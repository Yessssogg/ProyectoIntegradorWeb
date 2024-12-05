package modelo;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelo.Historia;
import modelo.Responsable;
import modelo.Turno;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2024-12-03T16:43:29")
@StaticMetamodel(Paciente.class)
public class Paciente_ extends Persona_ {

    public static volatile SingularAttribute<Paciente, Boolean> tiene_OS;
    public static volatile SingularAttribute<Paciente, Historia> historia;
    public static volatile SingularAttribute<Paciente, String> tipo_Sangre;
    public static volatile SingularAttribute<Paciente, Responsable> unResponsable;
    public static volatile ListAttribute<Paciente, Turno> listaTurnos;

}