package com.qingmei2.sample_androidtest.c_robolectric;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.qingmei2.sample_androidtest.R;

/**
 * Created by QingMei on 2017/8/1.
 * desc:
 */

public class C01Activity extends AppCompatActivity {

    private Button button;
    private CheckBox cb;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_robo);
        button = (Button) findViewById(R.id.btn01);
        cb = (CheckBox) findViewById(R.id.cb_box);
    }

    @Override
    protected void onResume() {
        super.onResume();
        button.setText("onResume");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        button.setText("onDestroy");
    }

    public void alertDialog(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        AlertDialog dialog = builder.setTitle("dialog")
                .setIcon(R.mipmap.ic_launcher)
                .setMessage("dialog")
                .create();
        dialog.dismiss();

        Toast.makeText(this,"hello robolectric",Toast.LENGTH_SHORT).show();
    }

    public void controlCb(View view) {
        if (cb.isChecked())
            cb.setChecked(false);
        else
            cb.setChecked(true);
    }
}
