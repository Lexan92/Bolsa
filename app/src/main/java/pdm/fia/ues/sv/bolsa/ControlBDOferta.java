package pdm.fia.ues.sv.bolsa;

/**
 * Created by Alex on 26/4/2016.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ControlBDOferta {
    private static final String[]camposOferta=new String[] {"ID_OFERTA","ID_EMPRESA","ID_CARGO","DESCRIPCION","SALARIO","VACANTE","FECHA_INICIO","TURNO","GENERO","EDAD_REQUERIDA","ANOS_EXPERIENCIA","TIPO_CONTRATACION","FECHA_EXPIRACION"};
    private static final String[] camposEmpresa=new String[]{"ID_EMPRESA","NOMBRE", "DIRECCION", "TELEFONO","WEB", "MISION","VISION","DESCRIPCION"};
private final Context context;
    private DatabaseHelper DBhelper;
    private SQLiteDatabase db;

    public ControlBDOferta(Context ctx){
        this.context=ctx;
        DBhelper = new DatabaseHelper(context);
    }

    private static class DatabaseHelper extends SQLiteOpenHelper{
        private static final String BASE_DATOS  = "pdm115.s3db";
        private static final int VERSION=1;

        public DatabaseHelper(Context context){
            super (context,BASE_DATOS,null,VERSION);}

         @Override
        public void onCreate(SQLiteDatabase db){
try {
    db.execSQL("create table OFERTA \n" +
            "(\n" +
            "   ID_OFERTA            integer                        not null,\n" +
            "   ID_EMPRESA           integer                        null,\n" +
            "   ID_CARGO             integer                        null,\n" +
            "   DESCRIPCION          varchar(100)                   null,\n" +
            "   SALARIO              integer                        null,\n" +
            "   VACANTE              integer                        null,\n" +
            "   FECHA_INICIO         date                           null,\n" +
            "   TURNO                varchar(1024)                  null,\n" +
            "   GENERO               varchar(1024)                  null,\n" +
            "   EDAD_REQUERIDA       integer                        null,\n" +
            "   ANOS_EXPERIENCIA     integer                        null,\n" +
            "   TIPO_CONTRATACION    varchar(1024)                  null,\n" +
            "   FECHA_EXPIRACION     date                           null,\n" +
            "   constraint PK_OFERTA primary key (ID_OFERTA)\n" +
            ");");

    db.execSQL("create table EMPRESA \n" +
            "(\n" +
            "   ID_EMPRESA           integer                        not null,\n" +
            "   NOMBRE               varchar(100)                   null,\n" +
            "   DIRECCION            varchar(100)                   null,\n" +
            "   TELEFONO             varchar(100)                   null,\n" +
            "   WEB                  varchar(100)                   null,\n" +
            "   MISION               varchar(100)                   null,\n" +
            "   VISION               varchar(100)                   null,\n" +
            "   DESCRIPCION          varchar(100)                   null,\n" +
            "   constraint PK_EMPRESA primary key (ID_EMPRESA)\n" +
            ");");

    db.execSQL("create table SOLICITUD \n" +
            "(\n" +
            "   FECHA                date                           not null,\n" +
            "   ID_SOLICITUD         integer                        not null,\n" +
            "   ID_OFERTA            integer                        null,\n" +
            "   ID_CANDIDATO         integer                        not null,\n" +
            "   constraint PK_SOLICITUD primary key (ID_SOLICITUD)\n" +

            ");");

}catch (SQLException e){
    e.printStackTrace();

}
        }


        @Override
        public void onUpgrade(SQLiteDatabase db,int oldVersion, int newVersion){

        }

    }


    public void abrir() throws SQLException{
        db=DBhelper.getWritableDatabase();
    }

    public void cerrar() { DBhelper.close();}

    public Oferta consultarOferta(int clave){
        int[] id={clave};
        //Cursor cursor = db.query("oferta",camposOferta,"clave=?", id,null,null,null);
        return null;
    }


    //fin ControlBDOferta
}
