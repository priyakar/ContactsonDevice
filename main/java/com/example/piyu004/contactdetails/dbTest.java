package com.example.piyu004.contactdetails;

/**
 * Created by Piyu004 on 4/4/2015.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class dbTest {

    public static final String KEY_ROWID = "_id";
    public static final String KEY_NAME = "person_name";
    public static final String KEY_FRIENDLINESS = "person_frnd";

    private static final String DATABASE_NAME = "friendly";
    private static final String DATABASE_TABLE = "dbtable";
    private static final int DATABASE_VERSION = 1;
    private DbHelper ourHelper;
    private final Context ourContext;
    private SQLiteDatabase ourDatabase;

    private static class DbHelper extends SQLiteOpenHelper{

        public DbHelper(Context context) {

            super(context, DATABASE_NAME, null, DATABASE_VERSION);
            // TODO Auto-generated constructor stub
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            // TODO Auto-generated method stub
            db.execSQL("CREATE TABLE " + DATABASE_TABLE + " (" +
                            KEY_ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                            KEY_NAME + " TEXT NOT NULL, " +
                            KEY_FRIENDLINESS + " TEXT NOT NULL);"
            );
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // TODO Auto-generated method stub
            db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
            onCreate(db);
        }

    }
    public dbTest (Context c){
        ourContext = c;
    }
    public dbTest open(){
        ourHelper = new DbHelper(ourContext);
        ourDatabase = ourHelper.getWritableDatabase();
        return this;
    }
    public void Close(){

    }
    public long createEntry(String name, String frnd) {
        // TODO Auto-generated method stub
        ContentValues cv = new ContentValues();
        cv.put(KEY_NAME, name);
        cv.put(KEY_FRIENDLINESS, frnd);
        return ourDatabase.insert(DATABASE_TABLE, null, cv);
    }
    public String getData() {
        // TODO Auto-generated method stub
        String [] columns= new String [] {KEY_ROWID,KEY_NAME,KEY_FRIENDLINESS};
        Cursor c = ourDatabase.query(DATABASE_TABLE, columns, null, null, null, null, null);
        String result = "get data";
        int iRow = c.getColumnIndex(KEY_ROWID);
        int iName = c.getColumnIndex(KEY_NAME);
        int iF = c.getColumnIndex(KEY_FRIENDLINESS);

        for (c.moveToFirst();!c.isAfterLast();c.moveToNext()){
            result = result + c.getString(iRow) + " " + c.getString(iName)+ " " + c.getString(iF)+ "\n";
        }

        return result;
    }
    public void updateDataBase(Long lEdit, String mname, String mfrnd) {
        // TODO Auto-generated method stub
        ContentValues cv = new ContentValues();
        cv.put(KEY_NAME, mname);
        cv.put(KEY_FRIENDLINESS, mfrnd);
        ourDatabase.update(DATABASE_TABLE, cv, KEY_ROWID + "= " + lEdit, null);

    }
}