package project.lenovo.slidewalkthroughdemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by LEVONO on 5/17/2017.
 */

public class DataBaseContact extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Clientdetail.db";
    public static final String TABLE_NAME = "Clientdetail_table";

    public  static final String col_1 = "NAME";
    public  static final String col_2 = "PHONE";
    public  static final String col_3 = "LOCATION";


    public DataBaseContact(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+ TABLE_NAME + "(NAME TEXT ,PHONE INTEGER,LOCATION TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE"+ TABLE_NAME + "IF EXISTS ");
    }
    public Boolean insertDataConc(String cname, String cphn, String cloc) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(col_1, cname);
        contentValues.put(col_2, cphn);
        contentValues.put(col_3, cloc);
        long result = db.insert(TABLE_NAME, null, contentValues );
        db.close();
        if(result== -1){
            return false;
        }else{
            return true;
        }
    }
    public Cursor getAllContact() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("Select * from " + TABLE_NAME, null);
        return res;
    }
    public Integer deleteData(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        int i = db.delete(TABLE_NAME, "NAME = ?", new String[]{name});
        return i;
    }
}
