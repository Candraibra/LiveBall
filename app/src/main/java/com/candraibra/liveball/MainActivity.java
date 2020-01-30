package com.candraibra.liveball;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Adapter adapter;
    private List<String> strings = new ArrayList<>();
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);
        addData(strings);
        adapter = new Adapter(strings);
        initRv();
    }

    private void initRv() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        adapter.setOnBottomReachedListener(new OnBottomReachedListener() {
            @Override
            public void onBottomReached(int position) {
                recyclerView.post(new Runnable() {
                    @Override
                    public void run() {
                        Log.d("loadMore", String.valueOf(adapter.getItemCount()));
                        addData(strings);
                        adapter.notifyDataSetChanged();
                    }
                });
            }
        });
    }

    private void addData(List<String> stringsList) {
        for (int i = 0; i < 50; i++) {
            stringsList.add("# " + (i + 1));
        }
    }
}
