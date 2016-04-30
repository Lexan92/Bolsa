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


public class OfertaMenuActivity extends Activity implements AdapterView.OnItemClickListener{
    ListView listView;
    OfertaArrayAdapter adapter;
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
                String result1=data.getStringExtra("consultaSalario");
                String result2=data.getStringExtra("consultaContrato");
                String result3=data.getStringExtra("consultaGenero");
            }
        }
    }
}
