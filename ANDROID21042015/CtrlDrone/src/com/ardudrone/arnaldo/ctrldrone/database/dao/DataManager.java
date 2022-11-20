package com.ardudrone.arnaldo.ctrldrone.database.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
import android.os.SystemClock;

import com.ardudrone.arnaldo.ctrldrone.database.TableDefinition.tbConfiguracaoTableDefinition;
import com.ardudrone.arnaldo.ctrldrone.database.dao.impl.tbConfiguracaoDao;
import com.ardudrone.arnaldo.ctrldrone.database.model.tbConfiguracao;

import java.util.List;

public class DataManager {
  
	public static final String AIXS_A_VAL_MAX      = "AIXS_A_VAL_MAX";
	public static final String AIXS_B_VAL_MAX      = "AIXS_B_VAL_MAX";
	public static final String AIXS_SIZE           = "AIXS_SIZE";
	public static final String AIXS_MARGIN_B       = "AIXS_MARGIN_B";
	public static final String AIXS_MARGIN_L       = "AIXS_MARGIN_L";
	public static final String AIXS_OFFSET 	    = "AIXS_OFFSET";
	public static final String AIXS_STICK_SIZE     = "AIXS_STICK_SIZE";
	public static final String CTRL_VOO_WIDHT      = "CTRL_VOO_WIDHT";
	public static final String CTRL_VOO_HEIGHT     = "CTRL_VOO_HEIGHT";
	public static final String CTRL_VOO_GRD_TAM_X1 = "CTRL_VOO_GRT_X1";
	public static final String CTRL_VOO_GRD_TAM_X2 = "CTRL_VOO_GRT_X2";
	public static final String CTRL_VOO_GRD_TAM_Y1 = "CTRL_VOO_GRT_Y1";
	public static final String CTRL_VOO_GRD_TAM_Y2 = "CTRL_VOO_GRT_Y2";
	public static final String CTRL_VOO_GRD_POS1   = "CTRL_VOO_GP1";
	public static final String CTRL_VOO_GRD_POS2   = "CTRL_VOO_GP2";
	public static final String CTRL_VOO_GRD_POS3   = "CTRL_VOO_GP3";
	public static final String CTRL_VOO_GYROSCOPE_POS_INI = "CTRL_VOO_GYROSCOPE_POS_INI";
	public static final String CTRL_VOO_GYROSCOPE_POS_VL = "CTRL_VOO_GYROSCOPE_POS_VL";
	public static final String DRONE_IP            = "DRONE_IP";
	public static final String DRONE_PORT          = "DRONE_PORT";
	public static final String DRONE_NOME          = "DRONE_NOME";
	public static final String DRONE_SENHA         = "DRONE_SENHA";
	public static final String MOTOR_QTD           = "MOTOR_QTD";
	public static final String MOTOR_RPM_MAX       = "MOTOR_RPM_MAX"; // Velocidade Maxima
	public static final String MOTOR_RPM_MIN       = "MOTOR_RPM_MIN"; // Velocidade Minima
	public static final String MOTOR_RPM_EST       = "MOTOR_RPM_EST"; // Velocidade Estavel
	public static final String MOTOR_RPM_DES_MAX   = "MOTOR_RPM_DES_MAX"; // Velocidade Velocidade Maior
	public static final String MOTOR_RPM_DES_MIM   = "MOTOR_RPM_DES_MIM"; // Velocidade Velocidade Maior

    private Context context;
    private SQLiteDatabase database;
    private tbConfiguracaoDao configuracaoDao;

    public DataManager(Context context){
        setContext(context);
        SQLiteOpenHelper openHelper = new OpenHelper(context, "CTRLDRONEDATABASE", null, 1);
        setDatabase(openHelper.getWritableDatabase());

        this.configuracaoDao = new tbConfiguracaoDao(new tbConfiguracaoTableDefinition(), database);
    }

    private void openDb(){
        if(! getDatabase().isOpen()){
            setDatabase(SQLiteDatabase.openDatabase(Environment.getDataDirectory() + "/data/com.ardudrone.arnaldo.ctrldrone/databases/ctrldronedatabase.db", null, SQLiteDatabase.OPEN_READWRITE));
        }
    }

    private void closeDb() {
        if (getDatabase().isOpen()) {
            getDatabase().close();
        }
    }

    @SuppressWarnings("unused")
	private void resetDb() {
        closeDb();
        SystemClock.sleep(500);
        openDb();
    }

    public tbConfiguracao getConfiguracao(long id){
        return getConfiguracaoDao().get(id);
    }

    public tbConfiguracao getConfiguracao(String chave){
        return getConfiguracaoDao().getByClause(" CHAVE = $CHAVE ", new String[] { chave });
    }

    public List<tbConfiguracao> getConfiguracaoList(){
        return getConfiguracaoDao().getAll();
    }

    public boolean deleteConfiguracao(long id){
        boolean result = false;
        getDatabase().beginTransaction();
        result = getConfiguracaoDao().delete(id);
        getDatabase().setTransactionSuccessful();
        getDatabase().endTransaction();
        return result;
    }

    public long saveConfiguracao(tbConfiguracao configuracao){
        long result = 0;
        try {
            getDatabase().beginTransaction();
            result = getConfiguracaoDao().save(configuracao);
            getDatabase().setTransactionSuccessful();
        } catch (Exception e) {
            e.printStackTrace();
        }
        getDatabase().endTransaction();
        return result;
    }

    public boolean updateConfiguracao(tbConfiguracao configuracao){
        boolean result = false;
        try {
            getDatabase().beginTransaction();
            getConfiguracaoDao().update(configuracao, configuracao.getIdConfiguracao());
            getDatabase().setTransactionSuccessful();
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        getDatabase().endTransaction();
        return result;
    }

    public Context getContext() {
        return context;
    }
    public void setContext(Context context) {
        this.context = context;
    }
    public SQLiteDatabase getDatabase() {
        return database;
    }
    public void setDatabase(SQLiteDatabase database) {
        this.database = database;
    }
    public tbConfiguracaoDao getConfiguracaoDao() {
        return this.configuracaoDao;
    }
    public void setConfiguracaoDao(tbConfiguracaoDao configuracaoDao) {
        this.configuracaoDao = configuracaoDao;
    }
    public long getLastInsertRowId()
    {
        long last_insert_rowid = 0;
        String query = "SELECT last_insert_rowid()";

        try {
            getDatabase().beginTransaction();

            Cursor cursor = getDatabase().rawQuery(query, null);

            cursor.moveToFirst();
            last_insert_rowid = cursor.getLong(0);
            cursor.close();

            getDatabase().setTransactionSuccessful();
        } catch (Exception e) {
            e.printStackTrace();
        }
        getDatabase().endTransaction();

        return last_insert_rowid;
    }

    public void Truncate(String table)
    {
        String query = "";

        try {
            getDatabase().beginTransaction();

            query = "DELETE FROM " + table + ";";
            getDatabase().execSQL(query);

            query = "DELETE FROM SQLITE_SEQUENCE WHERE name='" + table + "';";
            getDatabase().execSQL(query);

            getDatabase().setTransactionSuccessful();
        } catch (Exception e) {
            e.printStackTrace();
        }
        getDatabase().endTransaction();
    }

    public String GetParams(String param)
    {
        return this.getConfiguracao(param).getValor();
    }

}
