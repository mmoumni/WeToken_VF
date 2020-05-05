package com.example.wetoken_vf;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wetoken_vf.R;
import com.example.wetoken_vf.TokenJSON;

import java.util.ArrayList;

public class TokenListAdapter extends RecyclerView.Adapter<TokenListAdapter.viewholder> {

    private ArrayList<TokenJSON> list;
    private static int currentPosition = -1;
    private Context context;
private boolean isOpened = false;

    public static class viewholder extends RecyclerView.ViewHolder {

        public TextView txtNom,txtCurrency,txtType,txtValue;
        public ImageView imgProfile;
        LinearLayout linearLayout;

        public viewholder(View v) {
            super(v);
            txtNom = v.findViewById(R.id.txtNom);
            txtCurrency = v.findViewById(R.id.txtCurrency);
            txtType = v.findViewById(R.id.txtType);
            txtValue = v.findViewById(R.id.txtValue);
            linearLayout = v.findViewById(R.id.recyclerLinear);
        }
    }


    public TokenListAdapter(ArrayList<TokenJSON> myDataset,Context context) {
        list = myDataset;
        this.context = context;
    }


    @NonNull
    @Override
    public TokenListAdapter.viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_token, parent, false);


        return new TokenListAdapter.viewholder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull TokenListAdapter.viewholder holder,final int position) {
        final TokenJSON item = list.get(position);


        holder.txtNom.setText(item.getNom());
        holder.txtValue.setText(item.getValue());
        holder.txtType.setText(item.getType());
        holder.txtCurrency.setText(item.getCurrency());

        holder.linearLayout.setVisibility(View.GONE);


        if(currentPosition == position && holder.linearLayout.getVisibility() == View.GONE) {
            System.out.println("isopened");
            Animation slideDown = AnimationUtils.loadAnimation(context, R.anim.recycleranim);
            holder.linearLayout.setVisibility(View.VISIBLE);
            holder.linearLayout.startAnimation(slideDown);

        }

       /* if (currentPosition == position && holder.linearLayout.getVisibility() == View.VISIBLE) {

            System.out.println("not opened");
            //creating an animation
            Animation slideDown = AnimationUtils.loadAnimation(context, R.anim.recycleranimup);

            //toggling visibility,
            holder.linearLayout.setVisibility(View.GONE);

            //adding sliding effect
            holder.linearLayout.startAnimation(slideDown);
        }*/


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {





                //getting the position of the item to expand it
                currentPosition = position;

                //reloding the list
                notifyDataSetChanged();
            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }


}



