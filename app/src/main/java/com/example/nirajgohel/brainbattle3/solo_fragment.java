package com.example.nirajgohel.brainbattle3;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class solo_fragment extends Fragment {
    View v;
    private RecyclerView myrecyclerview;
    private List<items> items;

    public solo_fragment(){
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v=inflater.inflate(R.layout.solo_fragment,container,false);
        myrecyclerview=(RecyclerView)v.findViewById(R.id.recyclerview_id);
        RecyclerViewAdapter recyclerViewAdapter =new RecyclerViewAdapter(getContext(),items);
        myrecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        myrecyclerview.setAdapter(recyclerViewAdapter);
        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        items=new ArrayList<>();
        items.add(new items("1","Vatsal",R.mipmap.ic_launcher,R.mipmap.trophy3,"1200"));
        items.add(new items("2","Harsh",R.mipmap.ic_launcher,R.mipmap.trophy3,"1150"));
        items.add(new items("3","Nitant",R.mipmap.ic_launcher,R.mipmap.trophy3,"1100"));
        items.add(new items("4","Niraj",R.mipmap.ic_launcher,R.mipmap.trophy3,"1050"));
        items.add(new items("5","Manan",R.mipmap.ic_launcher,R.mipmap.trophy3,"1000"));
        items.add(new items("6","Uttam",R.mipmap.ic_launcher,R.mipmap.trophy3,"950"));
        items.add(new items("7","Harshit",R.mipmap.ic_launcher,R.mipmap.trophy3,"900"));
        items.add(new items("8","Vatsal",R.mipmap.ic_launcher,R.mipmap.trophy3,"800"));
        items.add(new items("9","harsh",R.mipmap.ic_launcher,R.mipmap.trophy3,"700"));
        items.add(new items("10","Nitant",R.mipmap.ic_launcher,R.mipmap.trophy3,"600"));
        items.add(new items("11","Niraj",R.mipmap.ic_launcher,R.mipmap.trophy3,"500"));
        items.add(new items("12","uttam",R.mipmap.ic_launcher,R.mipmap.trophy3,"400"));
        items.add(new items("13","manan",R.mipmap.ic_launcher,R.mipmap.trophy3,"300"));
        items.add(new items("14","harshit",R.mipmap.ic_launcher,R.mipmap.trophy3,"200"));
        items.add(new items("15","Vatsal",R.mipmap.ic_launcher,R.mipmap.trophy3,"100"));
    }
}
