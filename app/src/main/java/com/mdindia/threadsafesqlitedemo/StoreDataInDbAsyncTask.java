package com.mdindia.threadsafesqlitedemo;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Faheem.Raza on 15/06/2017.
 */

public class StoreDataInDbAsyncTask extends AsyncTask<Void, Void, Void>{
    private String threadName;
    private ArrayList<PersonInfoModel> personInfos;
    private DbHelper mHelper;
    private Context mContext;
    private static final String TAG = StoreDataInDbAsyncTask.class.getSimpleName();

    public StoreDataInDbAsyncTask(Context context, ArrayList<PersonInfoModel> personInfos, String threadName) {
        mContext = context;
        this.personInfos = personInfos;
        this.threadName = threadName;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        mHelper = DbHelper.getInstance();
        Log.e("Thread Started ===", threadName);
    }

    @Override
    protected Void doInBackground(Void... voids) {
        for (PersonInfoModel personInfo : personInfos){
                /*try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }*/
            mHelper.insertRecord(personInfo);
            Log.e(TAG + "===", threadName);
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        Log.e("Thread Stopped ===", threadName);
    }
}