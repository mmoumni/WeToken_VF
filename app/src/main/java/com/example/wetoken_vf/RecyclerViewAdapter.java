package com.example.wetoken_vf;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.daimajia.swipe.SimpleSwipeListener;
import com.daimajia.swipe.SwipeLayout;
import com.daimajia.swipe.adapters.RecyclerSwipeAdapter;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerSwipeAdapter<RecyclerViewAdapter.SimpleViewHolder> {

    public String username = "fbaatourzzww";
    private OnClickInterface listnr;

    public static class SimpleViewHolder extends RecyclerView.ViewHolder {
        SwipeLayout swipeLayout;
        TextView textViewPos;
        TextView textPurpose;
        TextView textAmount;
        TextView textDate;
        ImageView buttonDelete;
        ImageView imgType;



        public SimpleViewHolder(View itemView) {
            super(itemView);
            swipeLayout = (SwipeLayout) itemView.findViewById(R.id.swipe);
            textViewPos = (TextView) itemView.findViewById(R.id.position);

            textPurpose = (TextView) itemView.findViewById(R.id.txtPurpose);
            textDate = (TextView) itemView.findViewById(R.id.txtDate);
            textAmount = (TextView) itemView.findViewById(R.id.txtAmounnt);

            buttonDelete = (ImageView) itemView.findViewById(R.id.trash);
            imgType = (ImageView) itemView.findViewById(R.id.imgType);

         /*   itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d(getClass().getSimpleName(), "onItemSelected: " + textViewData.getText().toString());
                    Toast.makeText(view.getContext(), "onItemSelected: " + textViewData.getText().toString(), Toast.LENGTH_SHORT).show();
                }
            });
         */
        }
    }

    private Context mContext;
    private List<Contributions> mDataset;

    //protected SwipeItemRecyclerMangerImpl mItemManger = new SwipeItemRecyclerMangerImpl(this);

    public RecyclerViewAdapter(Context context, List<Contributions> objects, OnClickInterface listnr) {
        this.mContext = context;
        this.mDataset = objects;
        this.listnr = listnr;
    }

    @Override
    public SimpleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item, parent, false);
        return new SimpleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final SimpleViewHolder viewHolder, final int position) {
        String purpose = mDataset.get(position).getCtR_MOTIF();
        String date = mDataset.get(position).getCtR_DATE();
        String amount = mDataset.get(position).getCtR_AMOUNT() + " €";

        viewHolder.swipeLayout.setShowMode(SwipeLayout.ShowMode.LayDown);
        viewHolder.swipeLayout.addSwipeListener(new SimpleSwipeListener() {
            @Override
            public void onOpen(SwipeLayout layout) {
                YoYo.with(Techniques.Tada).duration(500).delay(100).playOn(layout.findViewById(R.id.trash));
            }
        });
        viewHolder.swipeLayout.setOnDoubleClickListener(new SwipeLayout.DoubleClickListener() {
            @Override
            public void onDoubleClick(SwipeLayout layout, boolean surface) {
                Toast.makeText(mContext, "DoubleClick", Toast.LENGTH_SHORT).show();
            }
        });
        viewHolder.buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* mItemManger.removeShownLayouts(viewHolder.swipeLayout);
                mDataset.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, mDataset.size());
                mItemManger.closeAllItems();*/
                Toast.makeText(view.getContext(), "Deleted " + viewHolder.textPurpose.getText().toString() + "!", Toast.LENGTH_SHORT).show();
            }
        });
        viewHolder.textViewPos.setText((position + 1) + ".");

        viewHolder.textPurpose.setText(purpose);
        viewHolder.textDate.setText(date);
        viewHolder.textAmount.setText(amount);

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listnr.onClick(mDataset.get(position), 1);
            }
        });

        if(username.equals(mDataset.get(position).getCtR_FROM()) ){
            viewHolder.imgType.setImageResource(R.drawable.fleshareceiver);
        } else {
            viewHolder.imgType.setImageResource(R.drawable.flechasender);
        }



        mItemManger.bindView(viewHolder.itemView, position);
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    @Override
    public int getSwipeLayoutResourceId(int position) {
        return R.id.swipe;
    }



    public interface OnClickInterface {
        void onClick(Contributions contribution, int id);
    }



}


