package com.example.admin.week1.helper;
/* *
 *  Created by JAY on 18/06/2018
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import com.example.admin.week1.model.Movie;
import java.util.ArrayList;
import java.util.List;

public class DbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "MOVIES_DB";
    private static final String TABLE_NAME = "MOVIES";
    private static final String COLUMN_ID = "ID";
    private static final String COLUMN_TITLE = "TITLE";
    private static final String COLUMN_OVER_VIEW = "OVER_VIEW";
    private static final String COLUMN_BACK_DROP_PATH = "BACK_DROP_PATH";
    private static final String COLUMN_VOTE_AVERAGE = "VOTE_AVERAGE";
    private static final String STR_CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY, "
                    + COLUMN_TITLE + " TEXT, "
                    + COLUMN_OVER_VIEW + " TEXT, "
                    + COLUMN_VOTE_AVERAGE + " REAL, "
                    + COLUMN_BACK_DROP_PATH + " TEXT "
                    + ")";

    private static DbHelper mInstance;

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("Thanh","ssss");
        db.execSQL(STR_CREATE_TABLE);
    }

    public static DbHelper newInstance(Context context) {
        if (mInstance == null) {
            mInstance = new DbHelper(context);
        }
        return mInstance;
    }

    public static DbHelper getInstance() {
        return mInstance;
    }

    public void insertMovies(List<Movie> movies) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from "+ TABLE_NAME);
        for (Movie movie : movies) {
            ContentValues values = new ContentValues();
            values.put(COLUMN_ID, movie.getId());
            values.put(COLUMN_TITLE, movie.getTitle());
            values.put(COLUMN_OVER_VIEW, movie.getOverview());
            values.put(COLUMN_VOTE_AVERAGE, movie.getVoteAverage());
            values.put(COLUMN_BACK_DROP_PATH, movie.getBackdropPath());
            db.insert(TABLE_NAME, null, values);
        }
        db.close();
    }

    public List<Movie> getMovies() {
        SQLiteDatabase db = this.getReadableDatabase();
        List<Movie> movies = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + TABLE_NAME;
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Movie movie = new Movie();
                movie.setId(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)));
                movie.setTitle(cursor.getString(cursor.getColumnIndex(COLUMN_TITLE)));
                movie.setOverview(cursor.getString(cursor.getColumnIndex(COLUMN_OVER_VIEW)));
                movie.setVoteAverage(cursor.getDouble(cursor.getColumnIndex(COLUMN_VOTE_AVERAGE)));
                movie.setBackdropPath(cursor.getString(cursor.getColumnIndex(COLUMN_BACK_DROP_PATH)));
                movies.add(movie);

            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return movies;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
