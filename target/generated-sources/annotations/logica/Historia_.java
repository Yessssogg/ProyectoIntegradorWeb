package logica;

import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import logica.Detalles;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2024-09-13T13:37:36")
@StaticMetamodel(Historia.class)
public class Historia_ { 

    public static volatile SingularAttribute<Historia, String> estado;
    public static volatile SingularAttribute<Historia, String> proximaCita;
    public static volatile ListAttribute<Historia, Detalles> detallesAtencion;
    public static volatile SingularAttribute<Historia, String> enfermedadesPrevias;
    public static volatile SingularAttribute<Historia, String> observaciones;
    public static volatile SingularAttribute<Historia, LocalDate> fechaCreacion;
    public static volatile SingularAttribute<Historia, Integer> id_historia;
    public static volatile SingularAttribute<Historia, String> cirugiasPrevias;
    public static volatile SingularAttribute<Historia, String> alergias;
    public static volatile SingularAttribute<Historia, String> tratamientosActuales;

}