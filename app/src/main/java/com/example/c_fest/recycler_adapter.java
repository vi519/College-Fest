package com.example.c_fest;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class recycler_adapter extends RecyclerView.Adapter<recycler_adapter.MyViewHolder> {

    private Context mContext ;
    private List<recycler_home> mData;


    public recycler_adapter(Context c,List<recycler_home> mData) {
        this.mContext = c;
        this.mData = mData;
    }

    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View v ;
        v = LayoutInflater.from(mContext).inflate(R.layout.recycler_home,viewGroup,false);
        MyViewHolder mHolder = new MyViewHolder(v);

        return mHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        final String cllg_edit = mData.get(i).getCllg_edit();
        final String date_pick = mData.get(i).getDate_pick();
        final String fest_edit = mData.get(i).getFest_edit();
        final String time_edit = mData.get(i).getTime_edit();
        final String venue_edit = mData.get(i).getVenue_edit();


        myViewHolder.tv_name.setText(mData.get(i).getFest_edit());
        myViewHolder.tv_cllg.setText(mData.get(i).getCllg_edit());


        myViewHolder.linearFest.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mContext,DetailActivity.class);
                i.putExtra("clgName",cllg_edit);
                i.putExtra("fstName",fest_edit);
                i.putExtra("venue",venue_edit);
                i.putExtra("date",date_pick);
                i.putExtra("time",time_edit);
                mContext.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }


    public  class MyViewHolder extends RecyclerView.ViewHolder{

        private TextView tv_name;
        private TextView tv_cllg;
        private ImageView img;
        private LinearLayout linearFest;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_name = (TextView) itemView.findViewById(R.id.fest_name);
            tv_cllg = (TextView)itemView.findViewById(R.id.cllg_name);
            img     = (ImageView)itemView.findViewById(R.id.profile_pic);
            linearFest = itemView.findViewById(R.id.linear_fest);
        }
    }
}
