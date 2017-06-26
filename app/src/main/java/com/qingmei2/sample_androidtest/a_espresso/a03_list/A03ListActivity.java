package com.qingmei2.sample_androidtest.a_espresso.a03_list;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.qingmei2.sample_androidtest.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class A03ListActivity extends AppCompatActivity {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.text_view)
    TextView text1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a03_list);
        ButterKnife.bind(this);
        
        initList();
    }

    private void initList() {
        text1.setBackgroundColor(Color.LTGRAY);
        text1.setVisibility(View.GONE);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new A03Adapter(30, new A03Adapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                text1.setText(String.valueOf(position));
                text1.setVisibility(View.VISIBLE);
            }
        }));
    }


}
