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




public class ControlBD {
    private static final String[] camposOferta = new String[]{"idoferta","idempresa","descripcion","salario","genero","idcategoria"};
    private static final String[] camposSolicitud = new String[]{"idsolicitud","idoferta","idcandidato","fechasolicitud"};
    private static final String[] campoEmpresa = new String[]{"idempresa","nombreempresa"};
    private static final String[] campoCandidato = new String[]{"idcandidato","nombre"};

    private final Context context;
    private DatabaseHelper DBhelper;
    private SQLiteDatabase db;


    public ControlBD(Context ctx) {
        this.context = ctx;
        DBhelper = new DatabaseHelper(context);
    }

    private static class DatabaseHelper extends SQLiteOpenHelper {

        private static final String BASE_DATOS = "mibase.s3db";
        private static final int VERSION=1;

        public DatabaseHelper(Context context) {
            super(context, BASE_DATOS, null, VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
           try{
               db.execSQL("CREATE TABLE candidato (idcandidato INTEGER NOT NULL PRIMARY KEY ,nombre VARCHAR(100));");
               db.execSQL("CREATE TABLE empresa (idempresa INTEGER NOT NULL PRIMARY KEY ,nombreempresa VARCHAR(100));");
               db.execSQL("CREATE TABLE oferta (idoferta INTEGER NOT NULL ,idempresa INTEGER NOT NULL ,descripcion VARCHAR(100) ,salario INTEGER ,genero VARCHAR(1) ,idcategoria INTEGER ,PRIMARY KEY(idoferta ,idempresa));");
               db.execSQL("CREATE TABLE solicitud (idsolicitud INTEGER NOT NULL,idoferta INTEGER NOT NULL, idcandidato INTEGER NOT NULL, PRIMARY KEY (idsolicitud,idoferta,idcandidato));");
           }catch (SQLException e){
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


    public boolean consultarOferta(){
// se realiza una consulta general unicamente para llenar el activity sin filtro
                Cursor cursor = db.rawQuery("SELECT * FROM oferta", null);



                    while (cursor.moveToNext()) {
                        Oferta oferta = new Oferta();
                        oferta.setId_oferta(cursor.getInt(0));
                        oferta.setId_empresa(cursor.getInt(1));
                        oferta.setDescripcion(cursor.getString(2));
                        oferta.setSalario(cursor.getInt(3));
                        oferta.setGenero(cursor.getString(4));
                        oferta.setCategoria(cursor.getInt(5));


                        DataSource.OFERTAS.add(oferta);

                    }


        return false;
    }

    public void consultarOfertaClave(int i){
        //consulta a oferta con un idoferta en especifico
        String cad ="SELECT* FROM oferta WHERE idoferta= "+Integer.toString(i)+" ;";

        Cursor cursor = db.rawQuery(cad,null);



            while (cursor.moveToNext()) {
                Oferta oferta = new Oferta();
                oferta.setId_oferta(cursor.getInt(0));
                oferta.setId_empresa(cursor.getInt(1));
                oferta.setDescripcion(cursor.getString(2));
                oferta.setSalario(cursor.getInt(3));
                oferta.setGenero(cursor.getString(4));
                oferta.setCategoria(cursor.getInt(5));

                DataSource.OFERTAS.add(oferta);

            }



    }


    public void consultarOferta2(String sal, String contra, String gen) {
//consulta a oferta con parametros de filtrado
        Cursor cursor = db.rawQuery("SELECT * FROM OFERTA WHERE EXIST( SELECT* FROM OFERTA WHERE SALARIO " + sal +
                ")AND WHERE EXIST(SELECT * FROM OFERTA WHERE TIPO_CONTRATACION " + contra + ") AND WHERE EXIST(SELECT * FROM OFERTA WHERE GENERO " + gen + ")", null);
        if (cursor.moveToFirst()) {
            while (cursor.moveToNext()) {
                Oferta oferta = new Oferta();
                oferta.setId_oferta(cursor.getInt(0));
                oferta.setId_empresa(cursor.getInt(1));
                oferta.setDescripcion(cursor.getString(2));
                oferta.setSalario(cursor.getInt(3));
                oferta.setGenero(cursor.getString(4));
                oferta.setCategoria(cursor.getInt(5));

                DataSource.OFERTAS.add(oferta);
            }
        }


    }

    public void consultarSolicitud(int clave) {
        //realiza una consulta a solicitudes y obtiene ofetas del candidato a las cuales ha aplicado
        Cursor cursor = db.rawQuery("SELECT idoferta FROM solicitud WHERE idcandidato = " + Integer.toString(clave) + ";", null);

            while (cursor.moveToNext()) {
                int key = cursor.getInt(0);
                consultarOfertaClave(key);
            }

    }

    public Empresa consultarEmpresa(int key){
        Cursor cursor = db.rawQuery("SELECT idempresa,nombreempresa FROM empresa WHERE idempresa = "+Integer.toString(key)+";",null);
        if(cursor.moveToFirst()){
            Empresa empresa = new Empresa();
            empresa.setId_empresa(cursor.getInt(0));
            empresa.setNombre_empresa(cursor.getString(1));
            return empresa;
        }else return null;
    }

    public String insertarOferta(Oferta oferta){
        long contador=0;
        String regInsertados="Aplicaste Correctamente";
        ContentValues ofer = new ContentValues();
        ofer.put("idoferta",oferta.getId_oferta());
        ofer.put("idempresa",oferta.getId_empresa());
        ofer.put("descripcion",oferta.getDescripcion());
        ofer.put("salario",oferta.getSalario());
        ofer.put("genero",oferta.getGenero());
        ofer.put("idcategoria",oferta.getCategoria());
        contador=db.insert("oferta",null,ofer);
        if(contador==-1 || contador==0)
        {
            regInsertados= "Error al Insertar el registro, Registro Duplicado. Verificar inserción";
        }
        else {
           // regInsertados=regInsertados+contador;
        }
        return regInsertados;

    }

    public String insertar(Candidato candidato){
        long contador=0;
        String regInsertados="Registro Insertado Nº= ";
        ContentValues can = new ContentValues();
        can.put("idcandidato",candidato.getId_candidato());
        can.put("nombre",candidato.getNombreCandidato());
        contador=db.insert("candidato",null,can);
        if(contador==-1 || contador==0)
        {
            regInsertados= "Error al Insertar el registro, Registro Duplicado. Verificar inserción";
        }
        else {
            regInsertados=regInsertados+contador;
        }
        return regInsertados;

    }


    public String insertar(Empresa empresa){
        long contador=0;
        String regInsertados="Registro Insertado Nº= ";
        ContentValues emp = new ContentValues();
        emp.put("idempresa",empresa.getId_empresa());
        emp.put("nombreempresa",empresa.getNombre_empresa());
        contador=db.insert("empresa",null,emp);
        if(contador==-1 || contador==0)
        {
            regInsertados= "Error al Insertar el registro, Registro Duplicado. Verificar inserción";
        }
        else {
            regInsertados=regInsertados+contador;
        }
        return regInsertados;
    }



    public String insertarSolicitud(Solicitud solicitud) {
        //reaiza una insercion en solicitud
        String regInsertados="Has Aplicado correctamente";
        long contador = 0;


        if(verificarIntegridad(solicitud,1)){
            ContentValues soli = new ContentValues();
            soli.put("idsolicitud", solicitud.id_solicitud);
            soli.put("idoferta", solicitud.id_oferta);
            soli.put("idcandidato", solicitud.id_candidato);




           contador= db.insert("solicitud", null, soli);
        }
        if(contador==1||contador==0){
            regInsertados= "Error al Insertar el registro :( ";
        }
        else {

            //regInsertados=regInsertados+contador;
        }
return regInsertados;
    }


    private boolean verificarIntegridad(Object dato, int relacion) throws SQLException {
        {
            switch (relacion) {
                case 1: {
                    Solicitud solicitud = (Solicitud) dato;
                    String[] id1 = {Integer.toString(solicitud.getId_oferta())};
                    String[] id2 = {Integer.toString(solicitud.getId_candidato())};

                    Cursor cursor1 = db.query("oferta",null,"idoferta= ?",id1,null,null,null);
                    Cursor cursor2 = db.query("candidato",null,"idcandidato = ?",id2,null,null,null);
                    if (cursor1.moveToFirst()&&cursor2.moveToFirst()) {
                        return true;
                    }
                    return false;

                }

                case 2: {

                }
                default:
                    return false;
            }//fin switch

        }

    }

    public String llenarBD(){

        Oferta oferta1 = new Oferta();
        Oferta oferta2 = new Oferta();
        Empresa empresa1=new Empresa();
        Candidato candidato1=new Candidato();
        Solicitud solicitud1=new Solicitud();
        //datos oferta1
        oferta1.setId_oferta(1);
        oferta1.setId_empresa(1);
        oferta1.setDescripcion("Programador experto VS");
        oferta1.setGenero("N");
        oferta1.setSalario(600);
        oferta1.setCategoria(R.drawable.worker);
        abrir();

    insertarOferta(oferta1);

        //datos oferta2
        oferta2.empresa.setId_empresa(1);
        oferta2.setDescripcion("Administrador de bases SQL SERVER");
        oferta2.setGenero("F");
        oferta2.setId_oferta(1);
        oferta2.setSalario(900);
        oferta2.setCategoria(R.drawable.worker); //error al ingresar int de dibujo

        insertarOferta(oferta2);

        //datos empresa
        empresa1.setId_empresa(1);
        empresa1.setNombre_empresa("Microsoft");

        insertar(empresa1);

        //datos candidato
        candidato1.setId_candidato(1);
        candidato1.setNombreCandidato("Jose Rodriguez");
        insertar(candidato1);

        //datos solicitud
        solicitud1.setId_solicitud(1);
        solicitud1.setId_oferta(1);
        solicitud1.setId_candidato(1);

      //  insertar(solicitud1);


        cerrar();
        return "guardado correctamente";
    }

    public int numeroRegistros(){
        return (int) DatabaseUtils.queryNumEntries(db,"solicitud");
    }





    }//fin ControlB