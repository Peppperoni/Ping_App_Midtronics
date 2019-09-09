package com.ping.ping_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

public class ListActivity extends AppCompatActivity
        implements ItemAdapter.ListItemClickListener{
    //private static final int NUM_LIST_ITEM = 100;
    private ItemAdapter mItemAdapter;
    private RecyclerView mRecyclerView;
    private Toast mToast;
    private String countryName = "";
    private String[] countryList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        mRecyclerView = (RecyclerView) findViewById(R.id.rv_countries);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);

        mRecyclerView.setHasFixedSize(true);

        mItemAdapter = new ItemAdapter(Countries.getCountryNames(), this);

        mRecyclerView.setAdapter(mItemAdapter);
    }

    //Click listener
    @Override
    public void onListItemClick(int clickItemIndex) {
        //click too fast
        if(mToast != null){
            mToast.cancel();
        }

        countryName = Countries.getCountryNames()[clickItemIndex];
        String toastMessage = countryName + " selected";
        mToast = Toast.makeText(this, toastMessage, Toast.LENGTH_LONG);
        mToast.show();

        Intent intent = new Intent(this, ShowCountryActivity.class);
        intent.putExtra("countryName",countryName);
        startActivity(intent);
    }
}
