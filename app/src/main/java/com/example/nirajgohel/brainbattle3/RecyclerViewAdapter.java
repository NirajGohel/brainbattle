package com.example.nirajgohel.brainbattle3;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.login.widget.ProfilePictureView;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    Context mContexr;
    List<items> mData;

    public RecyclerViewAdapter(Context mContexr, List<items> mData) {
        this.mContexr = mContexr;
        this.mData = mData;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        v=LayoutInflater.from(mContexr).inflate(R.layout.design_items,parent,false);
        MyViewHolder vHolder=new MyViewHolder(v);
        return  vHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.tv_srno.setText(mData.get(position).getSr_no());
        holder.tv_name.setText(mData.get(position).getPlyr_name());
        holder.tv_trophy.setText(mData.get(position).getPlyr_trophy());
        //holder.img1.setImageResource(mData.get(position).getPhoto_profile());
        holder.img2.setImageResource(mData.get(position).getPhoto_trophy());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        private TextView tv_srno;
        private TextView tv_name;
        private TextView tv_trophy;
        private ProfilePictureView img1;
        private ImageView img2;
        public MyViewHolder(View itemView){
            super(itemView);

            tv_srno=(TextView)itemView.findViewById(R.id.txt_srno);
            tv_name=(TextView)itemView.findViewById(R.id.txt_plyr);
            tv_trophy=(TextView)itemView.findViewById(R.id.txt_trophy);
            img1=(ProfilePictureView)itemView.findViewById(R.id.led_ProfilePicture);
            img2=(ImageView)itemView.findViewById(R.id.img_trophy);
        }
    }
}
