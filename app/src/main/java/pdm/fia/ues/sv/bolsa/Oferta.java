package pdm.fia.ues.sv.bolsa;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Alex on 25/4/2016.
 */
public class Oferta {
    int id_oferta;
   int id_empresa;
    int id_cargo;
    String descripcion;
    int salario;
    int vacantes;
   Date fecha_inicio;
   Date fecha_expiracion;
    String turno;
    String genero;
   int edad_requerida;
   int años_experiencia;
    String tipo_contratacion;

    private int categoria;
    Date ftransform;

    public Oferta(int id_oferta, int id_empresa, int id_cargo, String descripcion, int salario, int vacantes, Date fecha_inicio, Date fecha_expiracion, String turno, String genero, int edad_requerida, int años_experiencia, String tipo_contratacion) {
        this.id_oferta = id_oferta;
        this.id_empresa = id_empresa;
        this.id_cargo = id_cargo;
        this.descripcion = descripcion;
        this.salario = salario;
        this.vacantes = vacantes;
        this.fecha_inicio = fecha_inicio;
        this.fecha_expiracion = fecha_expiracion;
        this.turno = turno;
        this.genero = genero;
        this.edad_requerida = edad_requerida;
        this.años_experiencia = años_experiencia;
        this.tipo_contratacion = tipo_contratacion;
    }

    public Oferta() {

    }


    public int getId_oferta() {return id_oferta;}

    public void setId_oferta(int id_oferta) {
       this.id_oferta = id_oferta;
   }

    public int getId_empresa() {
        return id_empresa;}

    public void setId_empresa(String id_empresa) {
        //this.id_empresa = id_empresa;
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

    public int getVacantes() {
        return vacantes;
    }

    public void setVacantes(int vacantes) {
        this.vacantes = vacantes;
    }

    public Date getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(String fecha_inicio) {
        ftransform=transform(fecha_inicio);
        this.fecha_inicio = ftransform;
    }

   public Date getFecha_expiracion() {
        return fecha_expiracion;
   }

    public void setFecha_expiracion(String fecha_expiracion) {
        ftransform=transform(fecha_expiracion);
        this.fecha_expiracion = ftransform;
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

   public int getEdad_requerida() {
        return edad_requerida;
    }

    public void setEdad_requerida(int edad_requerida) {
        this.edad_requerida = edad_requerida;
    }

    public int getAños_experiencia() {
        return años_experiencia;
    }

    public void setAños_experiencia(int años_experiencia) {
        this.años_experiencia = años_experiencia;
    }

    public String getTipo_contratacion() {
        return tipo_contratacion;
    }

    public void setTipo_contratacion(String tipo_contratacion) {
        this.tipo_contratacion = tipo_contratacion;
    }

    public int getId_cargo() {
        return id_cargo;
    }

    public void setId_cargo(int id_cargo) {
        this.id_cargo = id_cargo;
    }

    public void setId_empresa(int id_empresa) {
        this.id_empresa = id_empresa;
    }


    //utilizado para asignar un icono

    public void setCategoria(int categoria) {
        this.categoria = categoria;

    }

    public int getCategoria(){return categoria;}

    //utilizado para el formato date transforma un string en date

    public Date transform(String cad){
        SimpleDateFormat dateFormat  = new SimpleDateFormat("yyyy-MM-dd");
        Date d=new Date();
        try{

            d=dateFormat.parse(cad);

        }catch (ParseException e){
            e.printStackTrace();
        }
        return d;
    }
}
