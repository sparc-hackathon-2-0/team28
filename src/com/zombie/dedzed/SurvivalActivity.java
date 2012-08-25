package com.zombie.dedzed;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class SurvivalActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survival);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_survival, menu);
        return true;
    }
}
