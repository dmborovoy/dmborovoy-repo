package com.borovoi.dmitrii.dbtutorial;

import android.app.Application;
import android.test.ApplicationTestCase;
import android.test.RenamingDelegatingContext;
import android.util.Log;

import com.borovoi.dmitrii.dbtutorial.model.CreditCard;
import com.borovoi.dmitrii.dbtutorial.model.User;

import java.util.List;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {
    public ApplicationTest() {
        super(Application.class);
    }

    private DBHelperV2 dbHelper;
    private static final String TAG = "<Test>";

    @Override
    public void setUp() throws Exception {
        super.setUp();
        RenamingDelegatingContext context = new RenamingDelegatingContext(getContext(), "test_");
        dbHelper = new DBHelperV2(context);
        assertNotNull(dbHelper);
        dbHelper.init();
//        SQLiteDatabase db = dbHelper.getWritableDatabase();
//        assertNotNull(db);
    }

    @Override
    public void tearDown() throws Exception {
        dbHelper.close();
        super.tearDown();
    }

    public void testAddUser(){
        User user = new User();
        user.setLogin("login");
        user.setPassword("123");
        user.setName("MyName");
        user.setIsAdmin(false);
        Log.i("<TEST>", "user=" + user);
        User user1 = dbHelper.createUser(user);
        assertNotNull(user1.getId());
        Log.i("<TEST>", "user1=" + user1);
    }

    public void testGetCardsForUser(){
        List<CreditCard> creditCardsByUserId = dbHelper.findAllCreditCardsByUserId(1L);
        assertEquals(4, creditCardsByUserId.size());
    }

    public void testGetUserForCreditCard(){
        User user = dbHelper.findUserForCreditCard(3L);
        assertEquals("user1", user.getName());
    }
}