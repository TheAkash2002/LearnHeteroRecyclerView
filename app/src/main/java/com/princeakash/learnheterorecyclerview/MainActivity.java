package com.princeakash.learnheterorecyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<Model> list = new ArrayList();
        list.add(new Model(Model.TEXT_TYPE, 0, "Hello guys, this is a sample of Text Type View Holder. Enjoy!"));
        list.add(new Model(Model.AUDIO_TYPE, R.raw.vishwaroop, "Click on the Floating Action Button below to listen to Vishwaroop."));
        list.add(new Model(Model.IMAGE_TYPE, R.drawable.prince_1, "Here's a working still from Prince:"));
        list.add(new Model(Model.TEXT_TYPE, 0, "Hope you find this useful! The above audio and image entry were made by AudioTypeViewHolder and ImageTypeViewHolder."));
        list.add(new Model(Model.AUDIO_TYPE, R.raw.surviva, "Click on the Floating Action Button below to listen to Surviva."));
        list.add(new Model(Model.IMAGE_TYPE, R.drawable.prince_2, "Here's another working still from Prince:"));
        list.add(new Model(Model.AUDIO_TYPE, R.raw.dhruva, "Click on the Floating Action Button below to listen to Dhruva."));
        list.add(new Model(Model.IMAGE_TYPE, R.drawable.prince_3, "Here's yet another working still from Prince:"));

        MultiViewAdapter adapter = new MultiViewAdapter(list, this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);

        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(adapter);
    }
}
