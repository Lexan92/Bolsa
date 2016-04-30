package pdm.fia.ues.sv.bolsa;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

public class Filtro extends AppCompatActivity {
    Button listo;

    String consultaSalario="";
    String consultaContrato="";
    String consultaGenero="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filtro);
    }

    public void metodo1(View v){
        CheckBox save1 = (CheckBox) findViewById(R.id.check1);
        if (save1.isChecked()){
            consultaSalario="BETWEEN '0' AND '200'";
        }else {

        }
    }

    public void metodo2(View v){
        CheckBox save2 = (CheckBox) findViewById(R.id.check2);
        if (save2.isChecked()){
            consultaSalario="BETWEEN '200' AND '500'";
        }else {

        }
    }

    public void metodo3(View v){
        CheckBox save3 = (CheckBox) findViewById(R.id.check3);
        if(save3.isChecked()){
            consultaSalario="SALARIO>500";
        }
    }

    public void metodo4(View v){
        CheckBox save4 = (CheckBox) findViewById(R.id.check4);
        if (save4.isChecked()){
            consultaContrato="IN ('tiempo parcial')";
        }
    }

    public void metodo5(View v){
        CheckBox save5 = (CheckBox) findViewById(R.id.check5);
        if(save5.isChecked()){
            consultaContrato="IN ('tiempo completo')";
        }
    }

    public void metodo6(View v){
        CheckBox save6 = (CheckBox) findViewById(R.id.check6);
        if(save6.isChecked()){
            consultaGenero="IN ('masculino')";
        }
    }

    public void metodo7(View v){
        CheckBox save7 = (CheckBox) findViewById(R.id.check7);
        if(save7.isChecked()){
            consultaGenero="IN ('femenino')";
        }
    }

    public void metodo8(View v){
        CheckBox save8 = (CheckBox) findViewById(R.id.check8);
        if(save8.isChecked()){
            consultaGenero="IN ('masculino','femenino')";
        }
    }




    public void enviarDatos(View view) {
        //creando  nuevo  intent de respuesta
        Intent databack = new Intent();
        //añadiendo el extra
        databack.putExtra("consultaSalario",consultaSalario);
        databack.putExtra("consultaContrato",consultaContrato);
        databack.putExtra("consultaGenero",consultaGenero);
        //devolver por el canal de forma exitosa el mensaje del intent
        setResult(RESULT_OK,databack);

        //terminar la actividad
        finish();
    }
}
