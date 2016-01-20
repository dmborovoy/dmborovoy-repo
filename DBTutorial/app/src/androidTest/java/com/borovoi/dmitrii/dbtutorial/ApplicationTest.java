package com.borovoi.dmitrii.dbtutorial;

import android.app.Application;
import android.test.ApplicationTestCase;
import android.test.RenamingDelegatingContext;
import android.util.Log;

import com.borovoi.dmitrii.dbtutorial.model.CreditCard;
import com.borovoi.dmitrii.dbtutorial.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {
    public ApplicationTest() {
        super(Application.class);
    }

    private DBHelperV3 dbHelper;
    private static final String TAG = "<Test>";
    int count = 500;
    long startTime = 0;
    long endTime = 0;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        RenamingDelegatingContext context = new RenamingDelegatingContext(getContext(), "test_");
        dbHelper = new DBHelperV3(context);
        assertNotNull(dbHelper);
//        dbHelper.initDB(d);
    }

    @Override
    public void tearDown() throws Exception {
        dbHelper.close();
        super.tearDown();
    }

    public void testAddSimple() {
        startTime = System.currentTimeMillis();
        dbHelper.simpleInsert(count);
        endTime = System.currentTimeMillis();
        Log.i(TAG, "Total simple insert time: " + (endTime - startTime) + "ms");
    }

    public void testBulk() {
        startTime = System.currentTimeMillis();
        dbHelper.bulkInsertUsingCursor(count);
        endTime = System.currentTimeMillis();
        Log.i(TAG, "Total bulk insert time: " + (endTime - startTime) + "ms");
    }

    public void testBulkWithStatement() {
        startTime = System.currentTimeMillis();
        dbHelper.bulkInsertUsingCursor(count);
        endTime = System.currentTimeMillis();
        Log.i(TAG, "Total bulk insert with statement time: " + (endTime - startTime) + "ms");
    }

//    public void testAddUser(){
//        User user = new User();
//        user.setLogin("login");
//        user.setPassword("123");
//        user.setName("MyName");
//        user.setIsAdmin(false);
//        Log.i("<TEST>", "user=" + user);
//        User user1 = dbHelper.createUser(user);
//        assertNotNull(user1.getId());
//        Log.i("<TEST>", "user1=" + user1);
//    }
//
//    public void testGetCardsForUser(){
//        List<CreditCard> creditCards = dbHelper.findAllCreditCardsByUserId(1L);
//        assertEquals(4, creditCards.size());
//    }
//
//    public void testGetUserForCreditCard(){
//        User user = dbHelper.findUserForCreditCard(3L);
//        assertEquals("user1", user.getName());
//    }
//
//    public void testGetUserForGroup(){
//        List<User> users = dbHelper.findAllUserForGroup(2L);
//        assertEquals(2, users.size());
//        Log.i(TAG, String.valueOf(users.get(0)));
//        Log.i(TAG, String.valueOf(users.get(1)));
//    }
}