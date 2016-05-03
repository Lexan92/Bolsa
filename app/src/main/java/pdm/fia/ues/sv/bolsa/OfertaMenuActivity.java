package pdm.fia.ues.sv.bolsa;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.Date;


public class OfertaMenuActivity extends Activity implements AdapterView.OnItemClickListener{
    ListView listView;
    OfertaArrayAdapter adapter;
    ControlBD helper;
    String result1;
    String result2;

    String result3;
    public final static int OPINION_REQUEST_CODE=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oferta_menu);

        //Instancia del ListView
        listView=(ListView) findViewById(R.id.listView);

        //Inicializar el adaptador con la fuente de datos
        adapter= new OfertaArrayAdapter(this, DataSource.OFERTAS);

        //Relacionando la lista con el adaptador
        listView.setAdapter(adapter);

        //Estableciendo la escucha
        listView.setOnItemClickListener(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id=item.getItemId();
        if(id==R.id.action_clear){
            //limpiar todos los elementos
            adapter.clear();
            return true;
        }
        return super.onOptionsItemSelected(item);

    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
    Oferta ofertaActual = (Oferta) adapter.getItem(position);
        final int extra_id_oferta= ofertaActual.getId_oferta();
        final int extra_id_empresa=ofertaActual.getId_empresa();
        final int extra_id_cargo=ofertaActual.getId_cargo();
        final String extra_descripcion=ofertaActual.getDescripcion();
        final int extra_salario=ofertaActual.getSalario();
        final int extra_vacante = ofertaActual.getVacantes();
        final Date extra_fecha_inicio=ofertaActual.getFecha_inicio();
        final Date extra_fecha_expirarion=ofertaActual.getFecha_expiracion();
        final String extra_turno=ofertaActual.getTurno();
        final String extra_genero=ofertaActual.getGenero();
        final int extra_edad_requerida=ofertaActual.getEdad_requerida();
        final String extra_tipo_contratacion=ofertaActual.getTipo_contratacion();

        Intent intent = new Intent(this,AplicacionActivity.class);
        intent.putExtra("var_oferta",extra_id_oferta);
        intent.putExtra("var_empresa",extra_id_empresa);
        intent.putExtra("var_cargo",extra_id_cargo);
        intent.putExtra("var_descripcion",extra_descripcion);
        intent.putExtra("var_salario",extra_salario);
        intent.putExtra("var_vacante",extra_vacante);
        intent.putExtra("var_fechai",extra_fecha_inicio);
        intent.putExtra("var_fechae",extra_fecha_expirarion);
        intent.putExtra("var_turno",extra_turno);
        intent.putExtra("var_genero",extra_genero);
        intent.putExtra("var_edad",extra_edad_requerida);
        intent.putExtra("extra_tipo",extra_tipo_contratacion);

        startActivity(intent);
    }


    public void miFiltro(View v) {
        Intent intent = new Intent(this,Filtro.class);
        startActivityForResult(intent,OPINION_REQUEST_CODE);
    }

    public void misAplicaciones(View v){
        Intent intent = new Intent(this,AplicacionActivity.class);
        startActivity(intent);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if (requestCode==OPINION_REQUEST_CODE){
            if (resultCode==RESULT_OK){
                 result1=data.getStringExtra("consultaSalario");
                 result2=data.getStringExtra("consultaContrato");
                result3=data.getStringExtra("consultaGenero");
            }
        }


    }


    public void actualizarLista(View v){
        DataSource.OFERTAS.clear();
        adapter.clear();
        helper.abrir();
        helper.consultarOferta2(result1,result2,result3);
        helper.cerrar();
        //Instancia del ListView
       // listView=(ListView) findViewById(R.id.listView);

        //Inicializar el adaptador con la fuente de datos
        adapter= new OfertaArrayAdapter(this, DataSource.OFERTAS);

        //Relacionando la lista con el adaptador
        listView.setAdapter(adapter);

        //Estableciendo la escucha
        listView.setOnItemClickListener(this);

    }

}
