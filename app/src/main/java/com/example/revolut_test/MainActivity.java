package com.example.revolut_test;

import android.app.Activity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

public class MainActivity extends Activity {

    private CurrencyAdapter adapter;
    private Timer timer;
    private ArrayList<Currency> initlist;
    private MakeRequests request;

    @BindView(R.id.recycler_view_items)
    RecyclerView rvContacts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        request = new MakeRequests();
        initlist = request.asyncrequest();
        rvContacts = findViewById(R.id.recycler_view_items);
        if (initlist == null)
            initlist = new ArrayList<>(20);
        adapter = new CurrencyAdapter(initlist , MainActivity.this);
        rvContacts.setAdapter(adapter);
        rvContacts.setItemViewCacheSize(initlist.size());
        rvContacts.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        start();
    }

    public void start(){
        timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                initlist = request.asyncrequest();
                adapter.updatecurrencies(initlist);
            }
        };
        timer.schedule(timerTask,0 , 1000);
    }

    public void stop(){
        timer.cancel();
    }

}