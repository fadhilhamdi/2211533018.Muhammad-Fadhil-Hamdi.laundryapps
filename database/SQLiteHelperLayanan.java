package database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import Model.ModelPelanggan;

public class SQLiteHelperLayanan extends SQLiteOpenHelper {
    public static String DATABASE_NAME = "my_laundry.db";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_LAYANAN = "layanan";
    public static final String KEY_LAYANAN_ID = "layanan_id";
    public static final String KEY_LAYANAN_NAMA = "nama";
    public static final String KEY_LAYANAN_LAYANAN = "layanan";
    public static final String KEY_LAYANAN_JUMLAH = "jumlah";
    private  static  final String CREATE_TABLE_LAYANAN = "CREATE TABLE " +
            TABLE_LAYANAN + "("
            + KEY_LAYANAN_ID + " TEXT, " + KEY_LAYANAN_NAMA + " TEXT, "+
            KEY_LAYANAN_LAYANAN + " TEXT, "+KEY_LAYANAN_JUMLAH +" TEXT )";

    public SQLiteHelperLayanan(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_LAYANAN);

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LAYANAN);
        onCreate(db);
    }
    public boolean insertPelanggan(ModelPelanggan mp){
        SQLiteDatabase database = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_LAYANAN_ID, mp.getId());
        contentValues.put(KEY_LAYANAN_NAMA, mp.getNama());
        contentValues.put(KEY_LAYANAN_LAYANAN, mp.getEmail());
        contentValues.put(KEY_LAYANAN_JUMLAH, mp.getHp());
        long id = database.insert(TABLE_LAYANAN, null, contentValues);
        database.close();
        if (id != -1){
            return  true;
        }else{
            return false;
        }
    }
    public List<ModelPelanggan> getPelanggan(){
        List<ModelPelanggan> pel = new ArrayList<ModelPelanggan>();
        String query = "SELECT * FROM " + TABLE_LAYANAN;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()){
            do{
                ModelPelanggan k = new ModelPelanggan();
                k.setId(cursor.getString(0));
                k.setNama(cursor.getString(1));
                k.setEmail(cursor.getString(2));
                k.setHp(cursor.getString(3));
                pel.add(k);
            }while (cursor.moveToNext());
        }
        return pel;
    }
}
