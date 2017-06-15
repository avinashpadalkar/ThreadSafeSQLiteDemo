package com.mdindia.threadsafesqlitedemo;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.BaseTransientBottomBar;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText edtFullName, edtEmail, edtAge, edtMobileNo;
    private Button btnSave;
    private Context mContext;
    private DbHelper mHelper;
    private ConstraintLayout parentLayout;
    private ArrayList<PersonInfoModel> mDummyData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialization();
        setListener();

        mDummyData = new ArrayList<>();
        mDummyData = dummyPersonRecord();
    }

    private void setListener() {
        btnSave.setOnClickListener(this);
    }

    private void initialization() {
        mContext = MainActivity.this;
        mHelper = DbHelper.getInstance();
        edtFullName = (EditText) findViewById(R.id.edtFullName);
        edtEmail = (EditText) findViewById(R.id.edtEmail);
        edtAge = (EditText) findViewById(R.id.edtAge);
        edtMobileNo = (EditText) findViewById(R.id.edtMobileNo);
        btnSave = (Button) findViewById(R.id.btnSave);
        parentLayout = (ConstraintLayout) findViewById(R.id.parentView);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnSave:
//                insertRecordToDatabase();
                StoreDataInDbAsyncTask storeDataInDbAsynctask1 =
                        new StoreDataInDbAsyncTask(mContext, mDummyData, "MainActivity Thread 1");
                storeDataInDbAsynctask1.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

                Intent intent = new Intent(mContext, AnotherActivity.class);
                intent.putParcelableArrayListExtra("personInfo", mDummyData);
                startActivity(intent);

                break;
        }
    }


    private boolean validate() {
        boolean isEmpty = false;
        if (edtFullName.getText().toString().equalsIgnoreCase("") ||
                edtMobileNo.getText().toString().equalsIgnoreCase("") ||
                edtEmail.getText().toString().equalsIgnoreCase("") ||
                edtAge.getText().toString().equalsIgnoreCase("")) {
            isEmpty = false;
        } else {
            isEmpty = true;
        }
        return isEmpty;
    }

    private void insertRecordToDatabase() {
        if (validate()) {
            PersonInfoModel personInfo = new PersonInfoModel();
            personInfo.setFullName(edtFullName.getText().toString());
            personInfo.setEmail(edtEmail.getText().toString());
            personInfo.setAge(edtAge.getText().toString());
            personInfo.setMobile_no(edtMobileNo.getText().toString());
            boolean isInsert = mHelper.insertRecord(personInfo);
            if (isInsert) {
                Snackbar.make(parentLayout, "Record Inserted.", Snackbar.LENGTH_SHORT).show();
                edtFullName.setText("");
                edtEmail.setText("");
                edtAge.setText("");
                edtMobileNo.setText("");
            } else {
                Snackbar.make(parentLayout, "Failed to insert record.", Snackbar.LENGTH_SHORT).show();

            }
        } else {
            Snackbar.make(parentLayout, "Empty field not allowed", BaseTransientBottomBar.LENGTH_SHORT).show();
        }
    }

    private ArrayList<PersonInfoModel> dummyPersonRecord() {
        ArrayList<PersonInfoModel> dummyInfo = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            dummyInfo.add(new PersonInfoModel("Test " + i, "test" + i + "@gmail.com", "" + (15 + i), "" + i));
        }
        return dummyInfo;
    }
}
