package mx.itson.yacaiste.persistence;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import mx.itson.yacaiste.entidades.BacheEntity;

import java.util.ArrayList;
import java.util.List;
/**
 * Created by EdgarAmador on 22/11/2015.
 */
public class YaCaisteDbManager {
    public static String
            INT_TYPE = "INTEGER",
            DOUBLE_TYPE = "REAL",
            FLOAT_TYPE = DOUBLE_TYPE,
            STRING_TYPE = "TEXT",
            AUTOINCREMENT = "AUTOINCREMENT",
            PRIMARY_KEY = "PRIMARY KEY",
            DATE_TYPE = "DATE";
    private final YaCaisteDBHelper helper;
    private final SQLiteDatabase wDb;

    public YaCaisteDbManager(Context context) {
        helper = new YaCaisteDBHelper(context, null);
        wDb = helper.getWritableDatabase();
    }

    public static class Columm {
        public String NAME;
        public String TYPE;

        public Columm(String name, String type) {
            this.NAME = name;
            this.TYPE = type;
        }
    }

    /**
     * Class Key representa a una clave de la base de datos
     * NAME = nombre de la clave
     * TYPE = typo de dato para la clave
     * AUTOINCREMENT = en caso de que sea numerica si es autoincrementable o no
     */
    public static class Key {
        public String NAME;
        public String TYPE;
        public boolean AUTOINCREMENT;

        public Key(String name, String type, boolean autoincrement) {
            this.NAME = name;
            this.TYPE = type;
            this.AUTOINCREMENT = autoincrement;
        }
    }

    public static String getQueryTable(String name, Key key, Columm[] columns) {
        String query = String.format("CREATE TABLE %s (", name);
        query += String.format(" %s %s %s ", key.NAME, key.TYPE, PRIMARY_KEY) + (key.AUTOINCREMENT ? AUTOINCREMENT : "");
        for (Columm columm : columns) {
            query += String.format(", %s %s ", columm.NAME, columm.TYPE);
        }
        return query + ");";
    }

    public static class Columns {
        public static String
                ID = "ID",
                LATITUD = "LATITUD",
                LONGITUD = "LONGITUD",
                DIRECCION = "DIRECCION",
                STATUS= "STATUS",
                FECHA = "FECHA",
                REPORTES = "REPORTES",
                FOTOGRAFIAS = "FOTOFRAFIAS";
    }

    public static class BacheTable {
        public static String NAME = "tblBache";
        public static Key KEY = new Key(Columns.ID, INT_TYPE, true);
        public static Columm[] COLUMNS = {
                new Columm(Columns.LATITUD, DOUBLE_TYPE),
                new Columm(Columns.LONGITUD, DOUBLE_TYPE),
                new Columm(Columns.DIRECCION, STRING_TYPE),
                new Columm(Columns.STATUS, STRING_TYPE),
                new Columm(Columns.FECHA, DATE_TYPE),
                new Columm(Columns.REPORTES, STRING_TYPE),
                new Columm(Columns.FOTOGRAFIAS, STRING_TYPE)
        };

    }
}
