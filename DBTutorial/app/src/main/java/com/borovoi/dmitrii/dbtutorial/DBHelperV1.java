package com.borovoi.dmitrii.dbtutorial;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.borovoi.dmitrii.dbtutorial.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dimas on 1/12/2016.
 */
public class DBHelperV1 extends SQLiteOpenHelper {

    private static final String TAG = "<DB>";

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "creditCardManagerDB";

    //common column names
    private static final String KEY_ID = "id";

    //table user
    private static final String TABLE_USER = "users";
    private static final String KEY_USER_NAME = "name";
    private static final String KEY_USER_LOGIN = "login";
    private static final String KEY_USER_PASSWORD = "password";
    private static final String KEY_USER_IS_ADMIN = "is_admin";
    private static final String[] COLUMNS_USER = new String[]{KEY_ID, KEY_USER_LOGIN, KEY_USER_NAME, KEY_USER_PASSWORD, KEY_USER_IS_ADMIN};

    private static final String CREATE_TABLE_USER = String.format(
            "CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT, %s TEXT, %s TEXT, %s INTEGER)",
            TABLE_USER, KEY_ID, KEY_USER_NAME, KEY_USER_LOGIN, KEY_USER_PASSWORD, KEY_USER_IS_ADMIN);

    private static final String DELETE_TABLE_USER = String.format(
            "DROP TABLE IF EXISTS %s",
            TABLE_USER);

    public DBHelperV1(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
//        init();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public void init() {
        Log.d(TAG, CREATE_TABLE_USER);
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(DELETE_TABLE_USER);
        db.execSQL(CREATE_TABLE_USER);
        createUser(new User("user1", "user1", "123", true));
        createUser(new User("user2", "user2", "123", true));
        createUser(new User("user3", "user3", "123", true));
        createUser(new User("user4", "user4", "123", true));
        createUser(new User("user5", "user5", "123", true));
        createUser(new User("user6", "user6", "123", false));
        createUser(new User("user7", "user7", "123", true));
        createUser(new User("user7", "user7", "123", true));
        createUser(new User("user7", "user7", "123", true));
        createUser(new User("user7", "user7", "123", true));
        createUser(new User("user7", "user7", "123", true));
        createUser(new User("user7", "user7", "123", false));
    }

    public void closeDB() {
        SQLiteDatabase db = this.getReadableDatabase();
        if (db != null && db.isOpen())
            db.close();
    }

    public User createUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_USER_NAME, user.getName());
        values.put(KEY_USER_LOGIN, user.getLogin());
        values.put(KEY_USER_PASSWORD, Util.encryptPassword(user.getPassword()));
        values.put(KEY_USER_IS_ADMIN, user.isAdmin());
        values.put(KEY_USER_NAME, user.getName());
        long id = db.insert(TABLE_USER, null, values);
        user.setId(id);
        return user;
    }

    public int updateUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_USER_NAME, user.getName());
        values.put(KEY_USER_LOGIN, user.getLogin());
        values.put(KEY_USER_PASSWORD, Util.encryptPassword(user.getPassword()));
        values.put(KEY_USER_IS_ADMIN, user.isAdmin());
        values.put(KEY_USER_NAME, user.getName());
        return db.update(TABLE_USER, values, KEY_ID + " = ?", new String[]{String.valueOf(user.getId())});
    }

    public void deleteUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_USER, KEY_ID + " = ?", new String[]{String.valueOf(user.getId())});
        db.close();
    }

    private User cursor2User(Cursor cursor) {
        User entity = new User();
        entity.setId(cursor.getLong(cursor.getColumnIndex(KEY_ID)));
        entity.setName(cursor.getString(1));
        entity.setLogin(cursor.getString(2));
        entity.setPassword(cursor.getString(3));
        entity.setIsAdmin(Util.int2bool(cursor.getInt(4)));
        return entity;
    }

    public List<User> findAllUsers() {
        List<User> notes = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_USER;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                notes.add(cursor2User(cursor));
            } while (cursor.moveToNext());
        }
        return notes;
    }

    public User findUserById(Long id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_USER,
                COLUMNS_USER, KEY_ID + "= ?", new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
            return cursor2User(cursor);
        } else {
            return null;
        }
    }

    public User findUserByLogin(String login) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_USER,
                COLUMNS_USER, KEY_USER_LOGIN + "= ?", new String[]{login}, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
            return cursor2User(cursor);
        } else {
            return null;
        }
    }
}
