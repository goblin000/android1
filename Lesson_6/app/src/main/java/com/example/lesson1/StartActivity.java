package com.example.lesson1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.LayoutInflater;

public class StartActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

//    private int []images =
//            {R.drawable.nature1, R.drawable.nature2, R.drawable.nature3, R.drawable.nature4,
//                    R.drawable.nature5, R.drawable.nature6, R.drawable.nature7};
//    private int []images;

    private RecycleAdapter adapter;

    private RecyclerView.LayoutManager layoutManager;

    private Resources resources;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);


        resources = getResources();
        TypedArray typedArray = resources.obtainTypedArray(R.array.pictures_moscow);
        int length = typedArray.length();
        int [] images = new int [length];
        for ( int i = 0 ; i < length; i++){
            images[i] = typedArray.getResourceId(i, 0 );
        }

        adapter = new RecycleAdapter(images);
        recyclerView.setAdapter(adapter);
    }
}