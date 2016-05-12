package pdm.fia.ues.sv.bolsa;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;

import android.widget.ListView;
import android.widget.Toast;

import java.io.Serializable;


public class OfertaMenuActivity extends Activity implements AdapterView.OnItemClickListener,Serializable{
    ListView listView;
    OfertaArrayAdapter adapter;
    ControlBD helper;
    String result1;
    String result2;
int cod =1; //variable de prueba
    String result3;
    public final static int OPINION_REQUEST_CODE=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oferta_menu);
        helper=new ControlBD(this);

        iniciarTodo();
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
        Intent intent = new Intent(this,SeleccionadoActivity.class);
        intent.putExtra("parametro", (Serializable) ofertaActual);
        startActivity(intent);
    }


    public void miFiltro(View v) {
        Intent intent = new Intent(this,Filtro.class);
        startActivityForResult(intent,OPINION_REQUEST_CODE);
    }

    public void misAplicaciones(View v){

        new aplicacionTask().execute();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
      //este metodo recibe datos de la activity filtro

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

    public void iniciarTodo(){
        helper.abrir();
        //String tost= helper.llenarBD();

       // Toast.makeText(this,tost,Toast.LENGTH_SHORT).show();


    boolean c= helper.consultarOferta();
    helper.cerrar();
    Toast.makeText(this,"Consulto a ofertas",Toast.LENGTH_SHORT).show();}


    private class aplicacionTask extends AsyncTask<Void,Void,Void>{


        @Override
        protected void onPreExecute() {
          Toast.makeText(getApplicationContext(),"Buscando tus aplicaciones...Espera",Toast.LENGTH_LONG).show();


        }

        @Override
        protected Void doInBackground(Void... voids) {
            Intent intent = new Intent(getApplicationContext(),AplicacionActivity.class);
            startActivity(intent);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {

            helper=new ControlBD(getApplicationContext());
            iniciarTodo();
            //adapter.notifyDataSetChanged();
            listView=(ListView) findViewById(R.id.listView);
           // Inicializar el adaptador con la fuente de datos
          adapter= new OfertaArrayAdapter(getApplicationContext(), DataSource.OFERTAS);

            //Relacionando la lista con el adaptador
            listView.setAdapter(adapter);

        }
    }

}//fin clase principal
