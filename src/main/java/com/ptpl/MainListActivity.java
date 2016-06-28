package com.ptpl;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class MainListActivity extends AppCompatActivity {
 public static TextView textu,textp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        textu= (TextView) findViewById(R.id.textu);
        textp = (TextView) findViewById(R.id.textp);

        SharedPreferences sharedPreferances = getSharedPreferences("mypref",MODE_PRIVATE);
        String textu1 = sharedPreferances.getString("Name", "");
        String textp1 =sharedPreferances.getString("Password","");
        textu.setText(textu1);
        textp.setText(textp1);

        RecyclerListFrag frag = new RecyclerListFrag();
        FragmentManager manger = getFragmentManager();
        FragmentTransaction transaction = manger.beginTransaction();
        transaction.add(R.id.container,frag,"frag1").commit();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

}
