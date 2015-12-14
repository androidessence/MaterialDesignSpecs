package com.androidessence.materialdesignspecs.sample;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.androidessence.materialdesignspecs.MaterialPalettes;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Get the LinearLayout used to display our TextViews
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.linear_layout);

        try {
            // Get all 500 level colors.
            List<Integer> fiveHundredColors = MaterialPalettes.getColorsByLevel(MaterialPalettes.LEVEL_500);

            // For each color, create a text view, set its text color, and display
            for(Integer color : fiveHundredColors) {
                TextView textView = new TextView(this);
                textView.setText("This is a 500 level color.");
                // getResources().getColor() was deprecated in API 23.
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    textView.setTextColor(getColor(color));
                } else {
                    textView.setTextColor(getResources().getColor(color));
                }
                linearLayout.addView(textView);
            }

        } catch(IllegalAccessException iae) {
            // Sample - Do nothing, not important. If it fails we will figure out why.
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
