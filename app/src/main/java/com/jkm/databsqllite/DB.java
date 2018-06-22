package com.jkm.databsqllite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by HP on 05-06-2018.
 */

public class DB extends SQLiteOpenHelper {

    public static final String dbn="DBSQLite.db";
    public static final String tbn="ITEMS";
    public static final String c1="ID";
    public static final String c2="Name";
    public static final String c3="Batch_No";
    public static final String c4="Price";

    public DB(Context context)
    {
        super(context,dbn,null,2);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL("Create table "+(tbn)+" (ID TEXT PRIMARY KEY UNIQUE,Name TEXT,Batch_No TEXT,Price TEXT)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS "+tbn);
        onCreate(db);
    }
    public Boolean dataInsert(String id,String i,String bn,String p)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(c1,id);
        cv.put(c2,i);
        cv.put(c3,bn);
        cv.put(c4,p);
        long result=db.insert(tbn,null,cv);
        if(result==-1)
            return false;
        else
            return true;
    }
    public Cursor dataShow()
    {
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("SELECT * FROM "+tbn,null);
        return cursor;
    }
    public Boolean dataUpdate(String id,String i,String bn,String p)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(c1,id);
        cv.put(c2,i);
        cv.put(c3,bn);
        cv.put(c4,p);
        //int rowsAffected=
        db.update(tbn,cv,"ID=?",new String[]{id});
        return true;
    }
    public Integer dataDelete(String id)
    {
        SQLiteDatabase db=getWritableDatabase();
        //int rowsAffected=
        return db.delete(tbn,"ID=?",new String[]{id});

    }
}
