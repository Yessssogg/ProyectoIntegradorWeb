package modelo;

import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelo.Detalles;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2024-12-03T16:43:29")
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