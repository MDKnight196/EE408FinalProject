package visual_environments.ee408finalproject;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.content.Context;
import android.os.CountDownTimer;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;


public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Context context = this;

        Intent intent;
        intent = new Intent(context, Settings.class);
        startActivity(intent);

    }
}


