package pdm.fia.ues.sv.bolsa;

/**
 * Created by Alex on 26/4/2016.
 *
 * ESTA CLASE ES UNICAMENTE PROVISIONAL!!!!!!!!!!!!!!
 *
 *
 *
 *
 */
public class Empresa {

    int id_empresa;
    String nombre_empresa;


    public Empresa(int id_empresa, String nombre_empresa) {
        this.id_empresa = id_empresa;
        this.nombre_empresa = nombre_empresa;
    }

    public int getId_empresa() {
        return id_empresa;
    }

    public void setId_empresa(int id_empresa) {
        this.id_empresa = id_empresa;
    }

    public String getNombre_empresa() {
        return nombre_empresa;
    }

    public void setNombre_empresa(String nombre_empresa) {
        this.nombre_empresa = nombre_empresa;
    }
}
