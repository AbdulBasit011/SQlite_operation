package com.example.sqlite_operation;


import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class myDbAdapter {
    myDbAdapter myhelper;
    public myDbAdapter(Context context)
    {
        myhelper=new myDBHelper(context);
    }

    public long insertData(String name, String pass)
    {
        SQLiteDatabase dbb = myhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(myDBHelper.NAME, name);
        contentValues.put(myDBHelper.MyPASSWORD, pass);
        long id = dbb.insert(myDBHelper.TABLE_NAME, null , contentValues);
        return id;
    }

    private SQLiteDatabase getWritableDatabase() {
    }

    public String getData()
    {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        String[] columns = {myDBHelper.UID,myDBHelper.NAME,myDBHelper.MyPASSWORD};
        Cursor cursor =db.query(myDBHelper.TABLE_NAME,columns,null,null,null,null,null);
        StringBuffer buffer= new StringBuffer();
        while (cursor.moveToNext())
        {
            int cid =cursor.getInt(cursor.getColumnIndex(.UID));
            @SuppressLint("Range") String name =cursor.getString(cursor.getColumnIndex(myDBHelper.NAME));
            @SuppressLint("Range") String  password =cursor.getString(cursor.getColumnIndex(myDBHelper.MyPASSWORD));
            buffer.append(cid+ "   " + name + "   " + password +" \n");
        }
        return buffer.toString();
    }

    public  int delete(String uname)
    {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        String[] whereArgs ={uname};

        int count =db.delete(myDBHelper.TABLE_NAME ,myDBHelper.NAME+" = ?",whereArgs);
        return  count;
    }

    public int updateName(String oldName , String newName)
    {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(myDBHelper.NAME,newName);
        String[] whereArgs= {oldName};
        int count =db.update(myDBAdapter.TABLE_NAME,contentValues, myDBHelper.NAME+" = ?",whereArgs );
        return count;
    }



















static class myDBHelper extends SQLiteOpenHelper {


    private static final String DATABASE_NAME = "myDatabase";    // Database Name
    private static final String TABLE_NAME = "myTable";   // Table Name
    private static final int DATABASE_Version = 1;   // Database Version
    private static final String UID="_id";     // Column I (Primary Key)
    private static final String NAME = "Name";    //Column II
    private static final String MyPASSWORD= "Password";    // Column III
    private static final String CREATE_TABLE = "CREATE TABLE "+TABLE_NAME+
            " ("+UID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+NAME+" VARCHAR(255) ,"+ MyPASSWORD+" VARCHAR(225));";
    private static final String DROP_TABLE ="DROP TABLE IF EXISTS "+TABLE_NAME;
    private Context context;

    public myDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_Version);
        this.context=context;
    }



