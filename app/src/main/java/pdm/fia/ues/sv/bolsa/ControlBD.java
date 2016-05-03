package pdm.fia.ues.sv.bolsa;

/**
 * Created by Alex on 26/4/2016.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.hardware.camera2.params.StreamConfigurationMap;
import android.widget.Toast;

import pdm.fia.ues.sv.bolsa.DataSource;

public class ControlBD {
    private static final String[] camposOferta = new String[]{"ID_OFERTA", "ID_EMPRESA", "ID_CARGO", "DESCRIPCION", "SALARIO", "VACANTE", "FECHA_INICIO", "TURNO", "GENERO", "EDAD_REQUERIDA", "ANOS_EXPERIENCIA", "TIPO_CONTRATACION", "FECHA_EXPIRACION"};
    private static final String[] camposEmpresa = new String[]{"ID_EMPRESA", "NOMBRE", "DIRECCION", "TELEFONO", "WEB", "MISION", "VISION", "DESCRIPCION"};
    private final Context context;
    private DatabaseHelper DBhelper;
    private SQLiteDatabase db;

    public ControlBD(Context ctx) {
        this.context = ctx;
        DBhelper = new DatabaseHelper(context);
    }

    private static class DatabaseHelper extends SQLiteOpenHelper {
        private static final String BASE_DATOS = "pdm115.s3db";
        private static final int VERSION = 1;

        public DatabaseHelper(Context context) {
            super(context, BASE_DATOS, null, VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
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

            } catch (SQLException e) {
                e.printStackTrace();

            }
        }


        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }

    }


    public void abrir() throws SQLException {
        db = DBhelper.getWritableDatabase();
    }

    public void cerrar() {
        DBhelper.close();
    }

    public void consultarOferta(String clave) {


        String[] id = {clave};
        Cursor cursor = db.query("OFERTA", camposOferta, "ID_OFERTA=?", id, null, null, "FECHA_INICIO", null);
        if (cursor.moveToFirst()) {

            while (cursor.moveToNext()) {
                Oferta oferta = new Oferta();
                oferta.setId_empresa(cursor.getInt(0));
                oferta.setId_empresa(cursor.getInt(1));
                oferta.setId_cargo(cursor.getInt(2));
                oferta.setDescripcion(cursor.getString(3));
                oferta.setSalario(cursor.getInt(4));
                oferta.setVacantes(cursor.getInt(5));
                oferta.setFecha_inicio(cursor.getString(6));
                oferta.setTurno(cursor.getString(7));
                oferta.setGenero(cursor.getString(8));
                oferta.setEdad_requerida(cursor.getInt(9));
                oferta.setAños_experiencia(cursor.getInt(10));
                oferta.setTipo_contratacion(cursor.getString(11));
                oferta.setFecha_expiracion(cursor.getString(12));

                DataSource.OFERTAS.add(oferta);
            }
        }

    }

    public void consultarOferta2(String sal, String contra, String gen) {

        Cursor cursor = db.rawQuery("SELECT * FROM OFERTA WHERE EXIST( SELECT* FROM OFERTA WHERE SALARIO " + sal +
                ")AND WHERE EXIST(SELECT * FROM OFERTA WHERE TIPO_CONTRATACION " + contra + ") AND WHERE EXIST(SELECT * FROM OFERTA WHERE GENERO " + gen + ")", null);
        if (cursor.moveToFirst()) {
            while (cursor.moveToNext()) {
                Oferta oferta = new Oferta();
                oferta.setId_empresa(cursor.getInt(0));
                oferta.setId_empresa(cursor.getInt(1));
                oferta.setId_cargo(cursor.getInt(2));
                oferta.setDescripcion(cursor.getString(3));
                oferta.setSalario(cursor.getInt(4));
                oferta.setVacantes(cursor.getInt(5));
                oferta.setFecha_inicio(cursor.getString(6));
                oferta.setTurno(cursor.getString(7));
                oferta.setGenero(cursor.getString(8));
                oferta.setEdad_requerida(cursor.getInt(9));
                oferta.setAños_experiencia(cursor.getInt(10));
                oferta.setTipo_contratacion(cursor.getString(11));
                oferta.setFecha_expiracion(cursor.getString(12));
                DataSource.OFERTAS.add(oferta);
            }
        }


    }

    public void consultarSolicitud(int clave) {
        Cursor cursor = db.rawQuery("SELECT ID_OFERTA FROM SOLICITUD WHERE ID_CANDIDATO = " + clave + " ORDER BY FECHA DESC", null);
        if (cursor.moveToFirst()) {
            while (cursor.moveToNext()) {
                int key = cursor.getInt(0);
                consultarOferta(Integer.toString(key));
            }
        }
    }


    public String insertarSolicitud(Solicitud solicitud) {
        String regInsertados="Has Aplicado correctamente";
        long contador = 0;


        if(verificarIntegridad(solicitud,1)){
            ContentValues soli = new ContentValues();
            soli.put("ID_SOLICITUD", solicitud.id_solicitud);
            soli.put("FECHA", solicitud.getFecha().toString());
            soli.put("ID_OFERTA", solicitud.id_oferta);
            soli.put("ID_CANDIDATO", solicitud.id_candidato);

            contador = db.insert("SOLICITUD", null, soli);
        }
        if(contador==1||contador==0){
            regInsertados= "Error al Insertar el registro :( ";
        }
        else {

            regInsertados=regInsertados+contador;
        }
return regInsertados;
    }


    private boolean verificarIntegridad(Object dato, int relacion) throws SQLException {
        {
            switch (relacion) {
                case 1: {
                    Solicitud solicitud = (Solicitud) dato;
                    String[] id = {Integer.toString(solicitud.getId_oferta())};

                    Cursor cursor = db.query("SOLICITUD", null, "ID_SOLICITUD = ?", id, null, null, null);
                    if (cursor.moveToFirst()) {
                        return false;
                    }
                    return true;

                }

                case 2: {

                }
                default:
                    return false;
            }//fin switch

        }

    }

    public String llenarBD(){

        return "guardado correctamente";
    }

    public int numeroRegistros(){
        return (int) DatabaseUtils.queryNumEntries(db,"SOLICITUD");
    }

    }//fin ControlB