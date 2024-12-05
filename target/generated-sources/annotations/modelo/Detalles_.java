package modelo;

import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelo.Historia;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2024-12-03T16:43:29")
@StaticMetamodel(Detalles.class)
public class Detalles_ { 

    public static volatile SingularAttribute<Detalles, String> motivoConsulta;
    public static volatile SingularAttribute<Detalles, Historia> historiaClinica;
    public static volatile SingularAttribute<Detalles, String> diagnostico;
    public static volatile SingularAttribute<Detalles, Integer> id_detalles;
    public static volatile SingularAttribute<Detalles, String> recetaMedica;
    public static volatile SingularAttribute<Detalles, LocalDate> fechaAtencion;
    public static volatile SingularAttribute<Detalles, String> tratamiento;

}