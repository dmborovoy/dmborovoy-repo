package com.borovoi.dmitrii.dbtutorial;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.borovoi.dmitrii.dbtutorial.model.CreditCard;
import com.borovoi.dmitrii.dbtutorial.model.Group;
import com.borovoi.dmitrii.dbtutorial.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dimas on 1/12/2016.
 */
public class DBHelperV2 extends SQLiteOpenHelper {

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


    public DBHelperV2(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public void init() {
        SQLiteDatabase db = this.getWritableDatabase();
        Log.d(TAG, CREATE_TABLE_USER);
        Log.d(TAG, CREATE_TABLE_GROUP);
        Log.d(TAG, CREATE_TABLE_CREDIT_CARD);
        Log.d(TAG, CREATE_TABLE_USER_GROUP_MAP);
        db.execSQL(DELETE_TABLE_USER);
        db.execSQL(DELETE_TABLE_GROUP);
        db.execSQL(DELETE_TABLE_CREDIT_CARD);
        db.execSQL(DELETE_TABLE_USER_GROUP_MAP);
        db.execSQL(CREATE_TABLE_USER);
        db.execSQL(CREATE_TABLE_GROUP);
        db.execSQL(CREATE_TABLE_CREDIT_CARD);
        db.execSQL(CREATE_TABLE_USER_GROUP_MAP);
        User user1 = new User("user5", "user5", "123", true);
        User user2 = new User("user2", "user2", "123", true);
        user1 = createUser(user1);
        user2 = createUser(user2);
        createUser(new User("user3", "user3", "123", true));
        createUser(new User("user4", "user4", "123", true));
        createUser(new User("user1", "user1", "123", true));
        createUser(new User("user6", "user6", "123", false));
        createUser(new User("user7", "user7", "123", true));
        createUser(new User("user7", "user7", "123", true));
        createUser(new User("user7", "user7", "123", true));
        createUser(new User("user7", "user7", "123", true));
        createUser(new User("user7", "user7", "123", true));
        createUser(new User("user7", "user7", "123", false));

        createCreditCard(new CreditCard("1111-2222-3333-4444", "654", "12/18"), user1.getId());
        createCreditCard(new CreditCard("2111-2222-3333-4444", "754", "11/18"), user1.getId());
        createCreditCard(new CreditCard("3111-2222-3333-4444", "854", "10/18"), user1.getId());
        createCreditCard(new CreditCard("4111-2222-3333-4444", "954", "09/18"), user1.getId());

        Group group1 = createGroup(new Group("My group1"));
        Group group2 = createGroup(new Group("My group2"));

        addUserToGroup(user1.getId(), group2.getId());
        addUserToGroup(user2.getId(), group2.getId());
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
