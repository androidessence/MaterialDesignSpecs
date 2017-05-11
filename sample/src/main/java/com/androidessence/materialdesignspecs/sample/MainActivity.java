package com.androidessence.materialdesignspecs.sample;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.androidessence.materialdesignspecs.ColorDialog;
import com.androidessence.materialdesignspecs.MaterialPalettes;

public class MainActivity extends AppCompatActivity implements ColorDialog.OnColorSelectedListener {

    private Toolbar toolbar;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_color_picker:
                try {
                    ColorDialog colorDialog = ColorDialog.newInstance(MaterialPalettes.getColorsByLevel(MaterialPalettes.LEVEL_500));
                    colorDialog.setOnColorSelectedListener(this);
                    colorDialog.show(getSupportFragmentManager(), "ColorPicker");
                } catch (IllegalAccessException iae) {
                    Log.e(MainActivity.class.getSimpleName(), iae.getMessage(), iae);
                }

                break;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * When a color is selected from the dialog, just change the background of the toolbar to verify
     * that it worked.
     * @param color The color that was selected from the dialog.
     */
    @Override
    public void onColorSelected(Integer color) {
        Integer colorRes = ContextCompat.getColor(this, color);
        toolbar.setBackgroundColor(colorRes);
    }
}
