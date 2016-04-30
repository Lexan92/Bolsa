package pdm.fia.ues.sv.bolsa;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class AplicacionActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{
    ListView listView1;
    OfertaArrayAdapter adapter1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aplicacion);
        //Instancia del ListView
        listView1=(ListView) findViewById(R.id.listView2);

        //Inicializar el adaptador con la fuente de datos
        adapter1= new OfertaArrayAdapter(this, DataSource.OFERTAS);

        //Relacionando la lista con el adaptador
        listView1.setAdapter(adapter1);

        //Estableciendo la escucha
        listView1.setOnItemClickListener(this);
    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id=item.getItemId();
        if(id==R.id.action_clear){
            //limpiar todos los elementos
            adapter1.clear();
            return true;
        }
        return super.onOptionsItemSelected(item);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Oferta ofertaActual = (Oferta) adapter1.getItem(position);

    }


}
