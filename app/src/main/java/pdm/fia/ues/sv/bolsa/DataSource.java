package pdm.fia.ues.sv.bolsa;



import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alex on 26/4/2016.
 */
public class DataSource {
    static List<Oferta> OFERTAS = new ArrayList<Oferta>();
    //String descripcion, int salario, int vacantes, String turno, String genero, String tipo_contratacion
    static {
        OFERTAS.add(new Oferta("Programador",800,5,"Medio Tiempo","Indeferente","plaza fija",R.drawable.worker));
        OFERTAS.add(new Oferta("Cocinero",300,7,"Tiempo Completo","Femenino","temporal",R.drawable.cook));
        OFERTAS.add(new Oferta("Programador",800,5,"Medio Tiempo","Indeferente","plaza fija",R.drawable.worker));
        OFERTAS.add(new Oferta("Cocinero",300,7,"Tiempo Completo","Femenino","temporal",R.drawable.cook));
        OFERTAS.add(new Oferta("Programador",800,5,"Medio Tiempo","Indeferente","plaza fija",R.drawable.worker));
        OFERTAS.add(new Oferta("Cocinero",300,7,"Tiempo Completo","Femenino","temporal",R.drawable.cook));
        OFERTAS.add(new Oferta("Programador",800,5,"Medio Tiempo","Indeferente","plaza fija",R.drawable.worker));
        OFERTAS.add(new Oferta("Cocinero",300,7,"Tiempo Completo","Femenino","temporal",R.drawable.cook));
    }
}
