package ro.pub.cs.systems.eim.practicaltest01var05.view;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ro.pub.cs.systems.eim.practicaltest01var05.R;
import ro.pub.cs.systems.eim.practicaltest01var05.general.Constants;
import ro.pub.cs.systems.eim.practicaltest01var05.service.PracticalTest01Var05Service;

public class PracticalTest01Var05MainActivity extends Activity {
    Button topLeftButton, topRightButton, navigateToSecondaryActivityButton, bottomLeftButton, bottomRighButton, centerButton;
    TextView resutView;
    int count;

    private IntentFilter intentFilter = new IntentFilter();

    private int serviceStatus = Constants.SERVICE_STOPPED;

    @Override
    protected void onDestroy() {
        Intent intent = new Intent(this, PracticalTest01Var05Service.class);
        stopService(intent);
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(messageBroadcastReceiver, intentFilter);
    }

    @Override
    protected void onPause() {
        unregisterReceiver(messageBroadcastReceiver);
        super.onPause();
    }

    private MessageBroadcastReceiver messageBroadcastReceiver = new MessageBroadcastReceiver();
    private class MessageBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d(Constants.BROADCAST_RECEIVER_TAG, intent.getStringExtra(Constants.BROADCAST_RECEIVER_EXTRA));
        }
    }

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
                case R.id.navigate_to_secondary_activity_button:
                    Intent intent = new Intent(getApplicationContext(), PracticalTest01Var05SecondaryActivity.class);
                    intent.putExtra(Constants.RESULT_OPERATION, resutView.getText().toString());
                    startActivityForResult(intent, Constants.SECONDARY_ACTIVITY_REQUEST_CODE);
                    break;
            }
            if (count > Constants.NUMBER_OF_CLICKS_THRESHOLD
                    && serviceStatus == Constants.SERVICE_STOPPED) {
                Intent intent = new Intent(getApplicationContext(), PracticalTest01Var05Service.class);
                List<String> sablon = Arrays.asList(resutView.getText().toString().split(","));
                ArrayList<String> list = new ArrayList<>(sablon);
                list.remove(0);
                intent.putExtra(Constants.SABLON, list);
                getApplicationContext().startService(intent);
                serviceStatus = Constants.SERVICE_STARTED;
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
        navigateToSecondaryActivityButton.setOnClickListener(buttonClickListener);
        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey(Constants.COUNT)) {
                Toast.makeText(
                        getBaseContext(), "Count value : " + savedInstanceState.getInt(Constants.COUNT),
                        Toast.LENGTH_LONG).show();
            }
        }
        intentFilter.addAction(Constants.actionType);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == Constants.SECONDARY_ACTIVITY_REQUEST_CODE) {
            Toast.makeText(this, "The activity returned with result " + resultCode, Toast.LENGTH_LONG).show();
        }
    }
}
