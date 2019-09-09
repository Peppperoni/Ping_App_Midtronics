package com.ping.ping_app;

import android.content.Context;
import android.nfc.Tag;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

/**
 * Created by lupin on 9/8/2019.
 */

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {
    private static final String TAG = ItemAdapter.class.getSimpleName();
    private int mNumberItems = 100;
    private static int viewHoldCount;

    private String[] countryNames;

    final private ListItemClickListener mOnClickListener;

    public ItemAdapter(String[] list, ListItemClickListener listener){
        //mNumberItems = numberOfItems;
        countryNames = list;
        mOnClickListener = listener;
        viewHoldCount = 0;
    }

    @Override
    public int getItemCount() {
        return mNumberItems;
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        Log.d(TAG, "#" + position);
        holder.bind(position);
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder
                                implements View.OnClickListener{
        TextView listItemView;

        @Override
        public void onClick(View view) {
            int clickedPosition = getAdapterPosition();
            mOnClickListener.onListItemClick(clickedPosition);
        }

        public ItemViewHolder(View itemView){
            super(itemView);

            listItemView = (TextView) itemView.findViewById(R.id.tv_item_number);
            itemView.setOnClickListener(this);
        }

        void bind(int listIndex){
            listItemView.setText(countryNames[listIndex]);
        }

    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.country_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately);
        ItemViewHolder viewHolder = new ItemViewHolder(view);

        //Change background color
        int backgroundColorForViewHolder = ColorUtils
                .getViewHolderBackgroundColorFromInstance(context, viewHoldCount);
        viewHolder.itemView.setBackgroundColor(backgroundColorForViewHolder);

        viewHoldCount++;

        return viewHolder;
    }

    //For the listener
    public interface ListItemClickListener{
        void onListItemClick(int clickItemIndex);
    }
}


