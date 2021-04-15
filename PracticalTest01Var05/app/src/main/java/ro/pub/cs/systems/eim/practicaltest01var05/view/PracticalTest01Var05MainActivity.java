package ro.pub.cs.systems.eim.practicaltest01var05.view;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import ro.pub.cs.systems.eim.practicaltest01var05.R;
import ro.pub.cs.systems.eim.practicaltest01var05.general.Constants;

public class PracticalTest01Var05MainActivity extends Activity {
    Button topLeftButton, topRightButton, navigateToSecondaryActivityButton, bottomLeftButton, bottomRighButton, centerButton;
    TextView resutView;
    int count;
    private ButtonClickListener buttonClickListener = new ButtonClickListener();
    private class ButtonClickListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.top_left_button:
                    resutView.setText(resutView.getText().toString() + ", " + Constants.TOP_LEFT);
                    count++;
                    break;
                case R.id.top_right_button:
                    resutView.setText(resutView.getText().toString() + ", " + Constants.TOP_RIGHT);
                    count++;
                    break;
                case R.id.bottom_right_button:
                    resutView.setText(resutView.getText().toString() + ", " + Constants.BOTTOM_RIGHT);
                    count++;
                    break;
                case R.id.bottom_left_button:
                    resutView.setText(resutView.getText().toString() + ", " + Constants.BOTTOM_LEFT);
                    count++;
                    break;
                case R.id.center_button:
                    resutView.setText(resutView.getText().toString() +  ", " + Constants.CENTER);
                    count++;
                    break;
            }
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var05_main);
        topLeftButton = findViewById(R.id.top_left_button);
        topRightButton = findViewById(R.id.top_right_button);
        bottomLeftButton = findViewById(R.id.bottom_left_button);
        bottomRighButton = findViewById(R.id.bottom_right_button);
        centerButton = findViewById(R.id.center_button);
        navigateToSecondaryActivityButton = findViewById(R.id.navigate_to_secondary_activity_button);
        resutView = findViewById(R.id.result_view);
        count = 0;
        topRightButton.setOnClickListener(buttonClickListener);
        topLeftButton.setOnClickListener(buttonClickListener);
        bottomRighButton.setOnClickListener(buttonClickListener);
        bottomLeftButton.setOnClickListener(buttonClickListener);
        centerButton.setOnClickListener(buttonClickListener);
        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey(Constants.COUNT)) {
                Toast.makeText(
                        getBaseContext(), "Count value : " + savedInstanceState.getInt(Constants.COUNT),
                        Toast.LENGTH_LONG).show();
            }
        }
    }
    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        if (savedInstanceState.containsKey(Constants.COUNT)) {
            Toast.makeText(
                    getBaseContext(), "Count value : " + savedInstanceState.getInt(Constants.COUNT),
                    Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putInt(Constants.COUNT, count);
    }
}
