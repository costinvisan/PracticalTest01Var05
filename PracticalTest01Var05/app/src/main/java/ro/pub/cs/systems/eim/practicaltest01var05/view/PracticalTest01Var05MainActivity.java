package ro.pub.cs.systems.eim.practicaltest01var05.view;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;

import androidx.annotation.Nullable;

import ro.pub.cs.systems.eim.practicaltest01var05.R;

public class PracticalTest01Var05MainActivity extends Activity {
    Button topLeftButton, topRightButton, navigateToSecondaryActivityButton, bottomLeftButton, bottomRighButton;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var05_main);
        topLeftButton = findViewById(R.id.top_left_button);
        topRightButton = findViewById(R.id.top_right_button);
        bottomLeftButton = findViewById(R.id.bottom_left_button);
        bottomRighButton = findViewById(R.id.bottom_right_button);
        navigateToSecondaryActivityButton = findViewById(R.id.navigate_to_secondary_activity_button);

    }
}
