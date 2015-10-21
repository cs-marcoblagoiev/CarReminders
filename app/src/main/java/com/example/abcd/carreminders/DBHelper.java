package com.example.abcd.carreminders;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;


//http://www.tutorialspoint.com/android/android_sqlite_database.htm
public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "MyCarsDB.db";
    public static final String CARS_TABLE_NAME = "cars";
    public static final String CARS_COLUMN_ID = "id";
    public static final String CARS_COLUMN_LICENCE = "licence";
    public static final String CARS_COLUMN_BRAND = "brand";
    public static final String CARS_COLUMN_USAGE = "usage";
    public static final String CARS_COLUMN_INSURANCE = "insurance";
    public static final String CARS_COLUMN_INSPECTION = "inspection";
    public static final String CARS_COLUMN_TAX = "tax";
    public static final String CARS_COLUMN_FIRE = "fire";
    public static final String CARS_COLUMN_MEDICAL = "medical";
    public static final String CARS_COLUMN_RATE = "rate";
    private HashMap hp;

    public DBHelper(Context context)
    {
        super(context, DATABASE_NAME , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL(
                "create table cars " +
                        "(id integer primary key, licence text,brand text,usage text, insurance text," +
                        "inspection text, tax text, fire text, medical text, rate text)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS cars");
        onCreate(db);
    }

    public boolean insertContact  (String licence, String brand, String usage, String insurance,
                                   String inspection, String tax, String fire, String medical, String rate)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("licence", licence);
        contentValues.put("brand", brand);
        contentValues.put("usage", usage);
        contentValues.put("insurance", insurance);
        contentValues.put("inspection", inspection);
        contentValues.put("tax", tax);
        contentValues.put("fire", fire);
        contentValues.put("medical", medical);
        contentValues.put("rate", rate);
        db.insert("cars", null, contentValues);
        return true;
    }

    public Cursor getData(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from cars where id="+id+"", null );
        return res;
    }

    public int numberOfRows(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, CARS_TABLE_NAME);
        return numRows;
    }

    public boolean updateCar (Integer id, String licence, String brand, String usage, String insurance,
                                  String inspection, String tax, String fire, String medical, String rate)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("licence", licence);
        contentValues.put("brand", brand);
        contentValues.put("usage", usage);
        contentValues.put("insurance", insurance);
        contentValues.put("inspection", inspection);
        contentValues.put("tax", tax);
        contentValues.put("fire", fire);
        contentValues.put("medical", medical);
        contentValues.put("rate", rate);
        db.update("cars", contentValues, "id = ? ", new String[] { Integer.toString(id) } );
        return true;
    }

    public Integer deleteCar (Integer id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("cars",
                "id = ? ",
                new String[] { Integer.toString(id) });
    }

    public ArrayList<String> getAllCars()
    {
        ArrayList<String> array_list = new ArrayList<String>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from cars", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_list.add(res.getString(res.getColumnIndex(CARS_COLUMN_LICENCE)));
            res.moveToNext();
        }
        return array_list;
    }
}