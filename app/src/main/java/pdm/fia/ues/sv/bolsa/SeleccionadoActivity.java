package pdm.fia.ues.sv.bolsa;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


public class SeleccionadoActivity extends Activity {
ControlBD helper;
    Oferta ayudador;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seleccionado);
    }


    public void iniciarAplicacion(View v){
        String regInsertados;
            Solicitud solicitud = new Solicitud();
        int reg=helper.numeroRegistros();
        //Intent intent =getIntent();
            Bundle datos = this.getIntent().getExtras();

        solicitud.setId_candidato(reg+1);
        solicitud.setFecha(ayudador.transform(getDatePhone()));
        int idOferta = datos.getInt("var_oferta");
        Candidato us=new Candidato();
        solicitud.setId_candidato(us.getId_candidato());

        helper.abrir();
        regInsertados=helper.insertarSolicitud(solicitud);
        helper.cerrar();
        Toast.makeText(this,regInsertados,Toast.LENGTH_SHORT).show();

    }

    public void cerrar(View v){
        finish();
    }

    private String getDatePhone()

    {

        Calendar cal = new GregorianCalendar();

        Date date = cal.getTime();

        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");

        String formatteDate = df.format(date);

        return formatteDate;

    }
}
