package com.qingmei2.sample_androidtest.a_espresso.a03_list;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class TextViewHolder extends RecyclerView.ViewHolder {
    public TextView textView;
    public TextViewHolder(View itemView) {
        super(itemView);
        textView = (TextView) itemView;
    }
}
