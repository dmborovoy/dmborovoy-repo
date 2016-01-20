package com.borovoi.dmitrii.dbtutorial;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.graphics.Path;
import android.util.Log;

import com.borovoi.dmitrii.dbtutorial.model.CreditCard;
import com.borovoi.dmitrii.dbtutorial.model.Group;
import com.borovoi.dmitrii.dbtutorial.model.User;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dimas on 1/12/2016.
 */
public class DBHelperV3 extends SQLiteOpenHelper {

    private static final String TAG = "<DB>";

    private static final int DB_VERSION = 1;
    //    private static final String DB_NAME = "credit_card_manager_db.sqlite";
    private static final String DB_NAME = "testSqlDB.sqlite";
    private static final String DB_FOLDER = "/data/data/" + BuildConfig.APPLICATION_ID + "/databases/";
    private static final String DB_PATH = DB_FOLDER + DB_NAME;
    private static final String DB_ASSETS_PATH = "db/" + DB_NAME;
    private static final int DB_FILES_COPY_BUFFER_SIZE = 8192;

    //common column names
    private static final String KEY_ID = "_id";

    //table user
    private static final String TABLE_USER = "users";
    private static final String KEY_USER_NAME = "name";
    private static final String KEY_USER_LOGIN = "login";
    private static final String KEY_USER_PASSWORD = "password";
    private static final String KEY_USER_IS_ADMIN = "is_admin";
    private static final String[] COLUMNS_USER = new String[]{KEY_ID, KEY_USER_LOGIN, KEY_USER_NAME, KEY_USER_PASSWORD, KEY_USER_IS_ADMIN};

    //table group
    private static final String TABLE_GROUP = "groups";
    private static final String KEY_GROUP_TITLE = "title";
    private static final String[] COLUMNS_GROUP = new String[]{KEY_ID, KEY_GROUP_TITLE};

    //table credit_card
    private static final String TABLE_CREDIT_CARD = "credit_cards";
    private static final String KEY_CREDIT_CARD_NUMBER = "card_number";
    private static final String KEY_CREDIT_CARD_CVV = "cvv";
    private static final String KEY_CREDIT_CARD_EXPIRY_DATE = "expiry_date";
    private static final String KEY_CREDIT_CARD_USER_ID_FOREIGN_KEY = "user_id";
    private static final String[] COLUMNS_CREDIT_CARD = new String[]{KEY_ID, KEY_CREDIT_CARD_NUMBER, KEY_CREDIT_CARD_CVV, KEY_CREDIT_CARD_EXPIRY_DATE};

    //table user_group_map
    private static final String TABLE_USER_GROUP_MAP = "user_group_map";
    private static final String KEY_USER_GROUP_USER_ID = "user_id";
    private static final String KEY_USER_GROUP_GROUP_ID = "group_id";

    private static final String CREATE_TABLE_USER = String.format(
            "CREATE TABLE %s (%s INTEGER PRIMARY KEY, %s TEXT, %s TEXT, %s TEXT, %s INTEGER)",
            TABLE_USER, KEY_ID, KEY_USER_NAME, KEY_USER_LOGIN, KEY_USER_PASSWORD, KEY_USER_IS_ADMIN);

    private static final String DELETE_TABLE_USER = String.format(
            "DROP TABLE IF EXISTS %s",
            TABLE_USER);

    private static final String CREATE_TABLE_GROUP = String.format(
            "CREATE TABLE %s (%s INTEGER PRIMARY KEY, %s TEXT)",
            TABLE_GROUP, KEY_ID, KEY_GROUP_TITLE);

    private static final String DELETE_TABLE_GROUP = String.format(
            "DROP TABLE IF EXISTS %s",
            TABLE_GROUP);

    private static final String CREATE_TABLE_CREDIT_CARD = String.format(
            "CREATE TABLE %s (%s INTEGER PRIMARY KEY, %s TEXT, %s TEXT, %s INTEGER, %s INTEGER," +
                    " FOREIGN KEY (%s) REFERENCES %s(%s))",
            TABLE_CREDIT_CARD, KEY_ID, KEY_CREDIT_CARD_NUMBER, KEY_CREDIT_CARD_CVV, KEY_CREDIT_CARD_EXPIRY_DATE, KEY_CREDIT_CARD_USER_ID_FOREIGN_KEY,
            KEY_CREDIT_CARD_USER_ID_FOREIGN_KEY, TABLE_USER, KEY_ID);

    private static final String DELETE_TABLE_CREDIT_CARD = String.format(
            "DROP TABLE IF EXISTS %s",
            TABLE_CREDIT_CARD);

    private static final String CREATE_TABLE_USER_GROUP_MAP = String.format(
            "CREATE TABLE %s (%s INTEGER NOT NULL, %s INTEGER NOT NULL," +
                    " PRIMARY KEY (%s, %s)," +
                    " FOREIGN KEY (%s) REFERENCES %s(%s)," +
                    " FOREIGN KEY (%s) REFERENCES %s(%s))",
            TABLE_USER_GROUP_MAP, KEY_USER_GROUP_USER_ID, KEY_USER_GROUP_GROUP_ID,
            KEY_USER_GROUP_USER_ID, KEY_USER_GROUP_GROUP_ID,
            KEY_USER_GROUP_USER_ID, TABLE_USER, KEY_ID,
            KEY_USER_GROUP_GROUP_ID, TABLE_GROUP, KEY_ID);

    private static final String DELETE_TABLE_USER_GROUP_MAP = String.format(
            "DROP TABLE IF EXISTS %s",
            TABLE_USER_GROUP_MAP);

    public DBHelperV3(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    public static boolean initialize(Context context) {
        boolean success = false;
        if (!isInitialized()) {
            success = copyInitialDBfromAssets(context);
        }
        return success;
    }

    public static boolean deleteDB(Context context) {
        return context.deleteDatabase(DB_NAME);
    }

    private static boolean isInitialized() {
        SQLiteDatabase checkDB = null;
        Boolean correctVersion = false;
        try {
            checkDB = SQLiteDatabase.openDatabase(DB_PATH, null, SQLiteDatabase.OPEN_READONLY);
            correctVersion = checkDB.getVersion() == DB_VERSION;
        } catch (SQLiteException e) {
            Log.w(TAG, e.getMessage());
        } finally {
            if (checkDB != null)
                checkDB.close();
        }
        return checkDB != null && correctVersion;
    }

    private static boolean copyInitialDBfromAssets(Context context) {
        boolean success = false;
        InputStream inStream = null;
        OutputStream outStream = null;
        try {
            File dbAsset = new File(DB_ASSETS_PATH);
            if (!dbAsset.exists())
                Log.d(TAG, "DB was not found in assets by path: " + DB_ASSETS_PATH);
            inStream = new BufferedInputStream(context.getAssets().open(
                    DB_ASSETS_PATH), DB_FILES_COPY_BUFFER_SIZE);
            String dbPath = context.getFilesDir().getPath() + BuildConfig.APPLICATION_ID + "/databases/";
            Log.d(TAG, "DB fodler is: " + DB_FOLDER);
            File dbDir = new File(DB_FOLDER);//            context.getFilesDir();
//            File dbDir = new File(context.getFilesDir().getPath() + "/" + BuildConfig.APPLICATION_ID + "/databases/");
            if (!dbDir.exists())
                dbDir.mkdir();
            outStream = new BufferedOutputStream(new FileOutputStream(DB_PATH),
                    DB_FILES_COPY_BUFFER_SIZE);
            byte[] buffer = new byte[DB_FILES_COPY_BUFFER_SIZE];
            int length;
            while ((length = inStream.read(buffer)) > 0)
                outStream.write(buffer, 0, length);
            outStream.flush();
            success = true;
        } catch (IOException ex) {
            Log.e(TAG, ex.getMessage());
            throw new RuntimeException("Fail to copy initial db from assets", ex);
        } finally {
            try {
                if (outStream != null) outStream.close();
                if (inStream != null) inStream.close();
            } catch (IOException e) {
                Log.e(TAG, e.getMessage());
            }
        }
        return success;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        initDB(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        switch (oldVersion) {

            case 1:
                //upgrade logic from version 1 to 2
            case 2:
                //upgrade logic from version 2 to 3
            case 3:
                //upgrade logic from version 3 to 4
                break;
            default:
                throw new IllegalStateException(
                        "onUpgrade() with unknown oldVersion " + oldVersion);
        }
    }

    public void initDB(SQLiteDatabase db) {
        db.execSQL(DELETE_TABLE_USER);
        db.execSQL(DELETE_TABLE_GROUP);
        db.execSQL(DELETE_TABLE_CREDIT_CARD);
        db.execSQL(DELETE_TABLE_USER_GROUP_MAP);
        db.execSQL(CREATE_TABLE_USER);
        db.execSQL(CREATE_TABLE_GROUP);
        db.execSQL(CREATE_TABLE_CREDIT_CARD);
        db.execSQL(CREATE_TABLE_USER_GROUP_MAP);
    }

    public void simpleInsert(int count) {
        Log.d(TAG, "Begin simple insert");
        for (int i = 0; i < count; i++) {
            createUser(new User("user" + i, "user" + i, "123", true));
        }
        Log.d(TAG, "End simple insert");
    }

    public long insert() {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("field1", "abc");
        return db.insert("table1", null, values);
    }

    public String get() {
        String result = "";
        String query = "SELECT * FROM table1";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                result = result + cursor.getString(0) + "," + cursor.getString(1) + ";\n";
            } while (cursor.moveToNext());
        }
        return result;
    }

    public void bulkInsertUsingCursor(int count) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.beginTransaction();
        Log.d(TAG, "Begin transaction");
        try {
            for (int i = 0; i < count; i++) {
                createUser(new User("user" + i, "user" + i, "123", true));
            }
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
        Log.d(TAG, "End transaction");
    }

    public void bulkInsertUsingStatement(int count) {
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = String.format("INSERT INTO %s VALUES(?, ?, ?, ?)", TABLE_USER);
        SQLiteStatement statement = db.compileStatement(sql);
        db.beginTransaction();
        Log.d(TAG, "Begin transaction");
        try {
            for (int i = 0; i < count; i++) {
                statement.clearBindings();
                statement.bindString(0, "user" + i);
                statement.bindString(1, "user" + i);
                statement.bindString(2, "123");
                statement.bindLong(3, 1);
                statement.execute();
            }
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
        Log.d(TAG, "End transaction");
    }


    public void closeDB() {
        SQLiteDatabase db = this.getReadableDatabase();
        if (db != null && db.isOpen())
            db.close();
    }

    //==============================User=========================//
    private User cursor2User(Cursor cursor) {
        User entity = new User();
        entity.setId(cursor.getLong(0));
        entity.setName(cursor.getString(1));
        entity.setLogin(cursor.getString(2));
        entity.setPassword(cursor.getString(3));
        entity.setIsAdmin(Util.int2bool(cursor.getInt(4)));
        return entity;
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


    public List<User> findAllUsers() {
        List<User> entities = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_USER;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                entities.add(cursor2User(cursor));
            } while (cursor.moveToNext());
        }
        return entities;
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

    public User findUserForCreditCard(Long creditCardId) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = String.format("SELECT u.* FROM %s c INNER JOIN %s u ON c.%s=u.%s WHERE c.%s=?",
                TABLE_CREDIT_CARD, TABLE_USER, KEY_CREDIT_CARD_USER_ID_FOREIGN_KEY, KEY_ID, KEY_ID);
        Log.d(TAG, query);
        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(creditCardId)});
        if (cursor != null) {
            cursor.moveToFirst();
            return cursor2User(cursor);
        } else {
            return null;
        }
    }
    //==============================End User=========================//

    //==============================CreditCard=========================//
    private CreditCard cursor2CreditCard(Cursor cursor) {
        CreditCard entity = new CreditCard();
        entity.setId(cursor.getLong(cursor.getColumnIndex(KEY_ID)));
        entity.setNumber(cursor.getString(cursor.getColumnIndex(KEY_CREDIT_CARD_NUMBER)));
        entity.setCvv(cursor.getString(cursor.getColumnIndex(KEY_CREDIT_CARD_CVV)));
        entity.setExpiryDate(cursor.getString(cursor.getColumnIndex(KEY_CREDIT_CARD_EXPIRY_DATE)));
        return entity;
    }

    public CreditCard findCreditCardById(Long id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_CREDIT_CARD,
                COLUMNS_CREDIT_CARD, KEY_ID + "= ?", new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
            return cursor2CreditCard(cursor);
        } else {
            return null;
        }
    }

    public CreditCard createCreditCard(CreditCard entity, Long userId) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_CREDIT_CARD_NUMBER, entity.getNumber());
        values.put(KEY_CREDIT_CARD_CVV, entity.getCvv());
        values.put(KEY_CREDIT_CARD_EXPIRY_DATE, entity.getExpiryDate());
        values.put(KEY_CREDIT_CARD_USER_ID_FOREIGN_KEY, userId);
        long id = db.insert(TABLE_CREDIT_CARD, null, values);
        entity.setId(id);
        return entity;
    }

    public List<CreditCard> findAllCreditCardsByUserId(Long userId) {
        SQLiteDatabase db = this.getReadableDatabase();
        List<CreditCard> entities = new ArrayList<>();
        String query = String.format("SELECT c.* FROM %s c INNER JOIN %s u ON c.%s=u.%s WHERE u.%s=?",
                TABLE_CREDIT_CARD, TABLE_USER, KEY_CREDIT_CARD_USER_ID_FOREIGN_KEY, KEY_ID, KEY_ID);
        Log.d(TAG, query);
        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(userId)});
        if (cursor.moveToFirst()) {
            do {
                entities.add(cursor2CreditCard(cursor));
            } while (cursor.moveToNext());
        }
        return entities;
    }
    //==============================End CreditCard=========================//

    //==============================Group=========================//
    private Group cursor2Group(Cursor cursor) {
        Group entity = new Group();
        entity.setId(cursor.getLong(cursor.getColumnIndex(KEY_ID)));
        entity.setTitle(cursor.getString(cursor.getColumnIndex(KEY_GROUP_TITLE)));
        return entity;
    }

    public Group findGroupById(Long id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_GROUP,
                COLUMNS_GROUP, KEY_ID + "= ?", new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
            return cursor2Group(cursor);
        } else {
            return null;
        }
    }

    public Group createGroup(Group entity) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_GROUP_TITLE, entity.getTitle());
        long id = db.insert(TABLE_GROUP, null, values);
        entity.setId(id);
        return entity;
    }

    public boolean addUserToGroup(Long userId, Long groupId) {
        boolean result = false;
//        TODO add checking
        User user = findUserById(userId);
        Group group = findGroupById(groupId);
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_USER_GROUP_USER_ID, userId);
        values.put(KEY_USER_GROUP_GROUP_ID, groupId);
        long id = db.insert(TABLE_USER_GROUP_MAP, null, values);
        Log.i(TAG, "what is returns?: " + id);
        return result;
    }

    public List<User> findAllUserForGroup(Long groupId) {
        SQLiteDatabase db = this.getReadableDatabase();
        List<User> entities = new ArrayList<>();
        String query = String.format("SELECT u.* FROM %s u INNER JOIN %s ug ON u.%s=ug.%s INNER JOIN %s g ON ug.%s=g.%s WHERE g.%s=?",
                TABLE_USER, TABLE_USER_GROUP_MAP, KEY_ID, KEY_USER_GROUP_USER_ID, TABLE_GROUP, KEY_USER_GROUP_GROUP_ID, KEY_ID, KEY_ID);
        Log.d(TAG, query);
        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(groupId)});
        if (cursor.moveToFirst()) {
            do {
                entities.add(cursor2User(cursor));
            } while (cursor.moveToNext());
        }
        return entities;
    }
    //==============================End Group=========================//


}
