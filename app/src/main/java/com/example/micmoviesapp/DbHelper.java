package com.example.micmoviesapp;

        import android.content.ContentValues;
        import android.content.Context;
        import android.database.Cursor;
        import android.database.sqlite.SQLiteDatabase;
        import android.database.sqlite.SQLiteOpenHelper;

        import androidx.annotation.Nullable;

        import java.util.ArrayList;

public class DbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME="movie_info";
    private static final String TABLE_NAME="movie_info1";
    private static final String COLUMN_ONE="movie_name";
    private static final String COLUMN_TWO="date";
    private static final String COLUMN_THREE="time_slot";
    private static final String COLUMN_FOUR="filled_num";
    private static final String COLUMN_FIVE="available_num";

    public DbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME,null, 1);
    }



    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //sd = this.getWritableDatabase();
        sqLiteDatabase.execSQL("CREATE TABLE movie_info1(movie_name TEXT,date TEXT,time_slot TEXT,filled_num INTEGER,available_num INTEGER,PRIMARY KEY(movie_name,date,time_slot))");

    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        onCreate(sqLiteDatabase);
    }


    public void addMovie(Movies movie)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(COLUMN_ONE,movie.name);
        cv.put(COLUMN_TWO,movie.date);
        cv.put(COLUMN_THREE,movie.time);
        cv.put(COLUMN_FOUR,movie.filled);
        cv.put(COLUMN_FIVE, movie.available);
        db.insert(TABLE_NAME,null, cv);
        db.close();

    }

    public ArrayList<Movies> getMovies()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Movies> movie=new ArrayList<>();
        Cursor cursor=db.rawQuery("SELECT * FROM "+ TABLE_NAME, null);
        while(cursor.moveToNext())
        {
            Movies md=new Movies();
            md.name= cursor.getString(0);
            md.date= cursor.getString(1);
            md.time= cursor.getString(2);
            md.filled=cursor.getInt(3);
            md.available=cursor.getInt(4);
            movie.add(md);
        }
        db.close();
        return movie;
    }

    public void updateMovies(String name) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] cols = {"filled_num","available_num"};
        Cursor c = db.query(TABLE_NAME,cols,COLUMN_ONE+"=?",new String[] {name},COLUMN_ONE,"","");
        c.moveToNext();
        int f = c.getInt(0);
        int a = c.getInt(1);
        ContentValues cv = new ContentValues();
        cv.put("filled_num",f+5);
        cv.put("available_num",a-5);
        SQLiteDatabase d = this.getWritableDatabase();
        d.update(TABLE_NAME,cv,COLUMN_ONE+"=?",new String[] {name});
        db.close();
    }

    public void deleteMovies(String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME,COLUMN_ONE+"=?",new String[] {name});
        db.close();
    }

    public ArrayList<String> movieNames() {
        ArrayList<String> a = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT movie_name from movie_info1",null);
        while (c.moveToNext()) {
            String s = c.getString(0);
            a.add(s);
        }
        db.close();
        return a;
    }

    public Movies getDetails(String s) {
        Movies m = new Movies();
        SQLiteDatabase db = this.getReadableDatabase();
        String[] cols = {COLUMN_ONE,COLUMN_TWO,COLUMN_THREE,COLUMN_FOUR,COLUMN_FIVE};
        Cursor c = db.query(TABLE_NAME,cols,COLUMN_ONE+"=?",new String[] {s},COLUMN_ONE,"","");
        c.moveToNext();
        m.name=c.getString(0);
        m.date=c.getString(1);
        m.time=c.getString(2);
        m.filled=c.getInt(3);
        m.available=c.getInt(4);
        db.close();
        return m;
    }


}
