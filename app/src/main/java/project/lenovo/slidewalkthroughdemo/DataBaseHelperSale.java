package project.lenovo.slidewalkthroughdemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by LEVONO on 5/17/2017.
 */

public class DataBaseHelperSale extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Supplier.db";
    public static final String TABLE_NAME = "Supplier_table";

    public static final String col_1 = "PRODUCT_NAME";
    public static final String col_2 = "CATEGORY";
    public static final String col_3 = "QUANTITY";
    public static final String col_4 = "PRICE";
    public static final String col_5 = "SUPPLIER";

    public DataBaseHelperSale(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + "(PRODUCT_NAME TEXT PRIMARY KEY,CATEGORY TEXT,QUANTITY INTEGER,PRICE INTEGER,SUPPLIER TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE" + TABLE_NAME + "IF EXISTS ");
    }


    public boolean insertDataSale(String pname, String pcategory, String pqnty, String prate, String psupply) {

        SQLiteDatabase dba = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(col_1, pname);
        contentValues.put(col_2, pcategory);
        contentValues.put(col_3, pqnty);
        contentValues.put(col_4, prate);
        contentValues.put(col_5, psupply);
        long result = dba.insert(TABLE_NAME, null, contentValues);
        dba.close();
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("Select * from " + TABLE_NAME, null);
        return res;
    }
    public Integer deleteData(String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        int i = db.delete(TABLE_NAME, "PRODUCT_NAME = ?", new String[]{name});
        return i;
    }

}
