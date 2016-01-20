package com.borovoi.dmitrii.dbtutorial;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;

import com.borovoi.dmitrii.dbtutorial.model.User;

import java.util.List;

public class MainActivityV1 extends AppCompatActivity {

    private static final String TAG = "<MYAPP>";
    List<User> users;
    User currentUser;
    int selectedUserIndex = -1;
    DBHelperV1 dbHelperV1;
    DBHelperV3 dbHelperV3;
    ArrayAdapter<User> adapterUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_v1);
        ListView listView = (ListView) findViewById(R.id.listView);

//        dbHelperV1 = new DBHelperV1(this);
//        dbHelperV1.init();
//        users = dbHelperV1.findAllUsers();
//
//        adapterUsers = new ArrayAdapter<User>(this, android.R.layout.simple_list_item_1, users);
//        listView.setAdapter(adapterUsers);
//        listView.setOnItemClickListener(itemClickListener);

        boolean deleted = DBHelperV3.deleteDB(this);
        Log.i(TAG, "deleted: " + deleted);
        boolean created = DBHelperV3.initialize(this);
        Log.i(TAG, "created: " + created);
        dbHelperV3 = new DBHelperV3(this);
        long insert = dbHelperV3.insert();
        Log.i(TAG, "inserted with id: " + insert);
        String s = dbHelperV3.get();
        Log.i(TAG, "content: " + s);

    }

    AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectedUserIndex = position;
            currentUser = users.get(selectedUserIndex);
            ((EditText) findViewById(R.id.editText)).setText(currentUser.getName());
            ((EditText) findViewById(R.id.editText2)).setText(currentUser.getLogin());
            ((EditText) findViewById(R.id.editText3)).setText(currentUser.getPassword());
            ((CheckBox) findViewById(R.id.checkBox)).setChecked(currentUser.isAdmin());
        }
    };

    public void onDeleteClicked(View view) {
        if (selectedUserIndex > 0) {
            dbHelperV1.deleteUser(currentUser);
            users.remove(selectedUserIndex);
            currentUser = null;
            ((EditText) findViewById(R.id.editText)).setText("");
            ((EditText) findViewById(R.id.editText2)).setText("");
            ((EditText) findViewById(R.id.editText3)).setText("");
            ((CheckBox) findViewById(R.id.checkBox)).setChecked(false);
            adapterUsers.notifyDataSetChanged();
            selectedUserIndex = -1;
        }
    }
}
