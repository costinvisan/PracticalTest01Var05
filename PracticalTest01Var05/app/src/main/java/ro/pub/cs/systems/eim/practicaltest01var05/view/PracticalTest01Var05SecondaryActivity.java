package ro.pub.cs.systems.eim.practicaltest01var05.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;

import ro.pub.cs.systems.eim.practicaltest01var05.R;
import ro.pub.cs.systems.eim.practicaltest01var05.general.Constants;

public class PracticalTest01Var05SecondaryActivity extends Activity {
    private TextView operationView;
    private Button correctButton, incorrectButton;

    private ButtonClickListener buttonClickListener = new ButtonClickListener();
    private class ButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.correct_button:
                    setResult(RESULT_OK, null);
                    break;
                case R.id.incorrect_button:
                    setResult(RESULT_CANCELED, null);
                    break;
            }
            finish();
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var05_secondary);

        operationView = findViewById(R.id.operation_result);
        correctButton = findViewById(R.id.correct_button);
        incorrectButton = findViewById(R.id.incorrect_button);

        Intent intent = getIntent();
        if (intent != null && intent.getExtras().containsKey(Constants.RESULT_OPERATION)) {
            String result = intent.getStringExtra(Constants.RESULT_OPERATION);
            operationView.setText(result);
        }

        correctButton.setOnClickListener(buttonClickListener);
        incorrectButton.setOnClickListener(buttonClickListener);
    }
}
