package pdm.fia.ues.sv.bolsa;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


public class SeleccionadoActivity extends Activity implements Serializable{
    ControlBD helper;
    Oferta ayudador;
    TextView empresaT;
    TextView descT;
    TextView sal;
    TextView gen;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seleccionado);
        helper=new ControlBD(this);
       Oferta ofert =(Oferta)getIntent().getExtras().getSerializable("parametro");
        helper.abrir();
        Empresa emp = helper.consultarEmpresa(ofert.getId_empresa());
        helper.cerrar();

        empresaT = (TextView) findViewById(R.id.empresaText);
        descT= (TextView) findViewById(R.id.descripcionText);
        sal = (TextView) findViewById(R.id.salarioText);
        gen = (TextView) findViewById(R.id.generoText);

        empresaT.setText(emp.getNombre_empresa());
        descT.setText(ofert.getDescripcion());
        sal.setText(Integer.toString(ofert.getSalario()));

        if(ofert.getGenero()=="F"){
            gen.setText("Femenino");
        }else if(ofert.getGenero()=="M"){
            gen.setText("Masculino");
        }else {
            gen.setText("Indeferente");
        }




    }


    public void iniciarAplicacion(View v){
        String regInsertados;
            Solicitud solicitud = new Solicitud();
        helper.abrir();
        int reg=helper.numeroRegistros();

        Oferta ofert =(Oferta)getIntent().getExtras().getSerializable("parametro");

        solicitud.setId_solicitud(reg+1);
        solicitud.setId_candidato(1);
        solicitud.setId_oferta(ofert.getId_oferta());



        regInsertados=helper.insertarSolicitud(solicitud);
        helper.cerrar();
        Toast.makeText(this,regInsertados,Toast.LENGTH_SHORT).show();

    }

    public void cerrar(View v){
        finish();
    }

}
