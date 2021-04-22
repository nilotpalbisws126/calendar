package com.example.calendar;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Message;
import android.widget.Toast;

import android.content.Context;
import android.widget.Toast;

class Messages {
    public static void message(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }
}



public class Database extends SQLiteOpenHelper
{
    private static final String DATABASE_NAME = "myDatabase";    // Database Name
    private static final String TABLE_NAME = "myTable";   // Table Name
    //  private static final int DATABASE_Version = 1;    // Database Version
    private static final String Datee = "Date";     // Column I (Primary Key)
    private static final String Type = "Type of event";    //Column II
    private static final String Description = "Description";    // Column III
    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME +
            " (" + Datee + " String ," + Type + " VARCHAR(255) ," + Description + " VARCHAR(225));";

    private static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
    private Context context;

    public Database(Context context)
    {
        super(context, DATABASE_NAME, null,1);
        this.context = context;
    }

    public void onCreate(SQLiteDatabase db)
    {

        try {
            Messages.message(context, "done");
            db.execSQL(CREATE_TABLE);
        } catch (Exception e) {
            Messages.message(context, "" + e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try {
            Messages.message(context, "OnUpgrade");
            db.execSQL(DROP_TABLE);
            onCreate(db);
        } catch (Exception e) {
            Messages.message(context, "" + e);
        }
    }

    public void insertData(String date, String Typee,String Descriptions)
    {

        ContentValues contentValues = new ContentValues();
        contentValues.put(Datee, date);
        contentValues.put(Type, Typee);
        contentValues.put(Description, Descriptions);
        SQLiteDatabase dbb = this.getWritableDatabase();
        System.out.println(date+" "+Type);
        dbb.insert(this.TABLE_NAME,null, contentValues);
        // Messages.message(,date+" "+Type+" "+Description);
        //  Toast.makeText(this,date+" "+Type+" "+Description,Toast.LENGTH_LONG).show();
        dbb.close();

    }


    public String getData(String date)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {this.Datee, this.Type, this.Description};
        Cursor cursor = db.query(this.TABLE_NAME, columns, null, null, null, null, null);
        StringBuffer buffer = new StringBuffer();
        // while (cursor.moveToNext())
        {
            String cid = cursor.getString(cursor.getColumnIndex(this.Datee));
            String name = cursor.getString(cursor.getColumnIndex(this.Type));
            String password = cursor.getString(cursor.getColumnIndex(this.Description));
            // if(date.equals(cid));
            buffer.append( name + "   " + password + " \n");
        }
        cursor.close();
        db.close();
        //  Toast.makeText(this,buffer,Toast.LENGTH_LONG).show();
        return buffer.toString();
    }

}