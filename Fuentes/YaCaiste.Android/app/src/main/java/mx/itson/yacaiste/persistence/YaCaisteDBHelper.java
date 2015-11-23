package mx.itson.yacaiste.persistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by EdgarAmador on 22/11/2015.
 */
public  class YaCaisteDBHelper extends SQLiteOpenHelper {
    private static int VERSION =1;
    private static String NAME="mx.itson.yacaiste.db";

    public YaCaisteDBHelper(Context context, SQLiteDatabase.CursorFactory factory ){
        super(context, NAME, factory, VERSION);
    }
 @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
     String query = YaCaisteDbManager.getQueryTable(YaCaisteDbManager.BacheTable.NAME, YaCaisteDbManager.BacheTable.KEY, YaCaisteDbManager.BacheTable.COLUMNS);
     sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(String.format("DROP TABLE IF EXISTS %s;",YaCaisteDbManager.BacheTable.NAME));
        onCreate(sqLiteDatabase);
    }
}
