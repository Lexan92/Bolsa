package pdm.fia.ues.sv.bolsa;

import java.util.Date;

/**
 * Created by Alex on 30/4/2016.
 */
public class Solicitud {
    int id_solicitud;
    Date fecha;
    int id_oferta;
    int id_candidato;

    public Solicitud(int id_solicitud,Date fecha, int id_oferta, int id_candidato) {
        this.id_solicitud = id_solicitud;
        this.fecha = fecha;

        this.id_oferta = id_oferta;
        this.id_candidato = id_candidato;
    }

    public Solicitud() {

    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }


    public int getId_oferta() {
        return id_oferta;
    }

    public void setId_oferta(int id_oferta) {
        this.id_oferta = id_oferta;
    }

    public int getId_candidato() {
        return id_candidato;
    }

    public void setId_candidato(int id_candidato) {
        this.id_candidato = id_candidato;
    }
}
