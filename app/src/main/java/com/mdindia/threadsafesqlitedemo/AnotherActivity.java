package com.mdindia.threadsafesqlitedemo;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

public class AnotherActivity extends AppCompatActivity {
    private Context mContext;
    private ArrayList<PersonInfoModel> mPersonInfoArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_another);

        initialization();
    }

    private void initialization() {
        mContext = AnotherActivity.this;
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            mPersonInfoArrayList = bundle.getParcelableArrayList("personInfo");
            new StoreDataInDbAsyncTask(mContext, mPersonInfoArrayList,
                    "AnotherActivity Thread 2").executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        }
    }
}
