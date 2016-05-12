package pdm.fia.ues.sv.bolsa;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Alex on 25/4/2016.
 */
public class Oferta implements Serializable{

    int id_oferta;
    int id_empresa;
    String descripcion;
    int salario;
    String genero;
    int categoria;
   public Empresa empresa;

    public Oferta() {

    }

    public int getId_oferta() {
        return id_oferta;
    }

    public void setId_oferta(int id_oferta) {
        this.id_oferta = id_oferta;
    }

    public int getId_empresa() {
        return this.id_empresa;
    }

    public void setId_empresa(int id_empresa) {
        this.id_empresa = id_empresa;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getSalario() {
        return salario;
    }

    public void setSalario(int salario) {
        this.salario = salario;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    //utilizado para asignar un icono

    public void setCategoria(int categoria) {
        this.categoria = categoria;

    }

    public int getCategoria(){return categoria;}


    //utilizado para el formato date transforma un string en date


}
