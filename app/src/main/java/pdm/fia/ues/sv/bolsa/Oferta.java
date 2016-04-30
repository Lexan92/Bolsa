package pdm.fia.ues.sv.bolsa;

import java.util.Date;

/**
 * Created by Alex on 25/4/2016.
 */
public class Oferta {
    //int id_oferta;
   // int id_empresa;
    String descripcion;
    int salario;
    int vacantes;
   // Date fecha_inicio;
   // Date fecha_expiracion;
    String turno;
    String genero;
   // int edad_requerida;
   // int años_experiencia;
    String tipo_contratacion;

    private int categoria;

    public Oferta(String descripcion, int salario, int vacantes, String turno, String genero, String tipo_contratacion, int categoria) {
        this.descripcion = descripcion;
        this.salario = salario;
        this.vacantes = vacantes;
        this.turno = turno;
        this.genero = genero;
        this.tipo_contratacion = tipo_contratacion;
        this.categoria = categoria;

    }

    //public int getId_oferta() {//return id_oferta;}

   // public void setId_oferta(int id_oferta) {
       // this.id_oferta = id_oferta;
   // }

    //public int getId_empresa() {
        //return id_empresa;
   // }

   // public void setId_empresa(int id_empresa) {
        //this.id_empresa = id_empresa;
   // }

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

    public int getVacantes() {
        return vacantes;
    }

    public void setVacantes(int vacantes) {
        this.vacantes = vacantes;
    }

   // public Date getFecha_inicio() {
        //return fecha_inicio;
    //}

    public void setFecha_inicio(Date fecha_inicio) {
        //this.fecha_inicio = fecha_inicio;
    }

  //  public Date getFecha_expiracion() {
        //return fecha_expiracion;
  //  }

    public void setFecha_expiracion(Date fecha_expiracion) {
        //this.fecha_expiracion = fecha_expiracion;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

   // public int getEdad_requerida() {
        //return edad_requerida;
   // }

    public void setEdad_requerida(int edad_requerida) {
        //this.edad_requerida = edad_requerida;
    }

   // public int getAños_experiencia() {
        ///return años_experiencia;
   // }

    public void setAños_experiencia(int años_experiencia) {
       // this.años_experiencia = años_experiencia;
    }

    public String getTipo_contratacion() {
        return tipo_contratacion;
    }

    public void setTipo_contratacion(String tipo_contratacion) {
        this.tipo_contratacion = tipo_contratacion;
    }


    //utilizado para asignar un icono

    public void setCategoria(int categoria) {
        this.categoria = categoria;

    }

    public int getCategoria(){return categoria;}
}
