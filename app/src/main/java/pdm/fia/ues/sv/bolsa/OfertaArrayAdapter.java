package pdm.fia.ues.sv.bolsa;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Alex on 25/4/2016.
 */
public class OfertaArrayAdapter extends ArrayAdapter<Oferta> {


    public OfertaArrayAdapter(Context context, List<Oferta> objects) {
        super(context, 0, objects);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //Obteniendo una instancia del inflater
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        //Salvando la referencia del View de la fila
        View listItemView = convertView;
        //Comprobando si el View no existe
        if (null == convertView) {
            //Si no existe, entonces inflarlo con image_list_view.xml
            listItemView = inflater.inflate(
                    R.layout.image_list_item, parent, false);
        }

        //Obteniendo instancias de los elementos
        TextView titulo = (TextView) listItemView.findViewById(R.id.text1);
        TextView subtitulo = (TextView) listItemView.findViewById(R.id.text2);
        ImageView categoria = (ImageView) listItemView.findViewById(R.id.category);

        //Obteniendo instancia de la Tarea en la posición actual
        Oferta item = getItem(position);

        titulo.setText(item.getDescripcion());
        subtitulo.setText(item.getGenero());
       categoria.setImageResource(item.getCategoria());

        return listItemView;
    }

}