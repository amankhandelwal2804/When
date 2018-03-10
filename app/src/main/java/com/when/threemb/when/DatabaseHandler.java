package com.when.threemb.when;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Freeware Sys on 10/15/2016.
 */
//NO NGROK

public class DatabaseHandler extends SQLiteOpenHelper {

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "When";

    // Contacts table name

    //    private static final String TABLE_NAME2 = "Attendance";
//    private static final String KEY_PPR_CODE = "ppr_code";
//    private static final String KEY_ROOM_NO = "room";
//    private static final String KEY_START_TIME = "starttime";
//    private static final String KEY_END_TIME = "endtime";
//    private static final String KEY_DAY = "day";
//    private static final String KEY_ROLL = "roll";
//    private static final String KEY_NAME = "name";

    private static final String TABLE_NAME = "Votes";
    private static final String KEY_MOTION_NO = "motionNo";
    private static final String KEY_ANSWER = "answer";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                +KEY_MOTION_NO + " INTEGER,"
                +KEY_ANSWER +" INTEGER"+")";
        db.execSQL(CREATE_CONTACTS_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

        // Create tables again
        onCreate(db);
    }
    /*void addMotion(int motionNo) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_MOTION_NO, motionNo);
        values.put(KEY_ANSWER, -1);
        db.insert(TABLE_NAME,null,values);
        db.close(); // Closing database connection
    }*/

    //Changing Timetable Object to MyTimetable
    public int getVote(int motionNo) {
        // Select All Query
        String selectQuery = "SELECT "+KEY_ANSWER+" FROM " + TABLE_NAME+ " WHERE "+KEY_MOTION_NO+" = "+motionNo;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst() && cursor!=null ) {
            /*do {
                MyTimetable to = new MyTimetable(cursor.getString(0),
                        cursor.getString(1),
                        Integer.parseInt( cursor.getString(2)),
                        Integer.parseInt(cursor.getString(3)),
                        Integer.parseInt(cursor.getString(4)),
                        Integer.parseInt( cursor.getString(5)),
                        cursor.getString(6));
                // Adding contact to list
                periodList.add(to);
            } while (cursor.moveToNext());*/
            return Integer.parseInt(cursor.getString(0));
        }
        else
        {
            ContentValues values = new ContentValues();
            values.put(KEY_MOTION_NO, motionNo);
            values.put(KEY_ANSWER, -1);
            db.insert(TABLE_NAME,null,values);
            db.close();
            return -1;
        }
        // return contact list
        //return -1;
    }





    void updateAnswer(int motionNo,int answer) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ANSWER, answer);

        long newRowId=db.update(TABLE_NAME, values,KEY_MOTION_NO+"="+motionNo,null/*new String[]{Integer.toString(motionNo)}*/);
        db.close(); // Closing database connection
    }

    /*public void deleteEntry(String Ppr_code,int startTime)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String selectQuery = "DELETE FROM " + TABLE_NAME2+ " WHERE "+KEY_PPR_CODE+" = \""+Ppr_code+"\" AND StartTime = "+startTime;
        db.execSQL(selectQuery);
        db.close();
    }

    public String[] sabBhejo(){
        SQLiteDatabase db=this.getReadableDatabase();
        String selectQuery="SELECT DISTINCT "+KEY_PPR_CODE+" FROM " +TABLE_NAME2;
        Cursor cursor=db.rawQuery(selectQuery,null);
        int i=0;
        String arr[]=new String[15];
        if(cursor!=null)
        {
            do{
                arr[i]=cursor.getString(0);
            }while(cursor.moveToNext());
        }
        return arr;
    }


    public ArrayList<AttendanceObject> getAllStudentDisplay() {
        ArrayList<AttendanceObject> attendanceList = new ArrayList<AttendanceObject>();
        // Select All Query

        //String selectQuery = "SELECT "+KEY_NAME+" AND "+KEY_ROLL + " FROM " + TABLE_NAME2;
        String selectQuery = "SELECT * FROM " + TABLE_NAME2;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if(cursor!=null) {
            if (cursor.moveToFirst()) {
                do {
                    int starttym=Integer.parseInt(cursor.getString(6));
                    AttendanceObject ao = new AttendanceObject(
                            starttym+" "+cursor.getString(0),
                            Integer.parseInt(cursor.getString(1)),
                            cursor.getString(2),
                            Integer.parseInt(cursor.getString(3)),
                            Integer.parseInt(cursor.getString(4)),
                            Integer.parseInt(cursor.getString(5)));

                    // Adding contact to list
                    attendanceList.add(ao);
                } while (cursor.moveToNext());
            }
        }

        // return contact list
        return attendanceList;
    }*/
}

