package visual_environments.ee408finalproject;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class Sensor_page extends AppCompatActivity {
    private int numSensor;
    private LinearLayout llContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        numSensor=SimulationManager.getSimulationSetup().getSensorCount();
        String SensorNum = "Sensor/Channel ";
        String Alpha= "Alpha: ";
        String h= "h: ";
        String n= "n: ";
        llContainer = (LinearLayout) findViewById(R.id.ll_Container);

       /* for (int i=0;i<numSensor;i++){
            View childLayout = getLayoutInflater().inflate(R.layout.sensor_display, null);
            childLayout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            childLayout.setId(i);

            llContainer.addView(childLayout);

        }*/

        for (Sensor s : SimulationManager.getLastSimulation().getSensorList()) {
            TextView Tittle = new TextView(Sensor_page.this);
            Tittle.setId(s.getID());
            Tittle.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            Tittle.setTextSize(30);
            Tittle.setTypeface(Typeface.DEFAULT_BOLD);
            Tittle.setText(SensorNum+s.getID());
            llContainer.addView(Tittle);

            TextView alpha = new TextView(Sensor_page.this);
            alpha.setId(100+s.getID());
            alpha.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            alpha.setTextSize(28);
            alpha.setText(Alpha+s.getAlpha().toFormattedString());
            llContainer.addView(alpha);

            TextView H = new TextView(Sensor_page.this);
            H.setId(200+s.getID());
            H.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            H.setTextSize(28);
            H.setText(h+s.getHVal().toFormattedString());
            llContainer.addView(H);

            TextView N = new TextView(Sensor_page.this);
            N.setId(300+s.getID());
            N.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            N.setTextSize(28);
            N.setText(n+s.getHVal().toFormattedString());
            llContainer.addView(N);

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
        Intent intent;
        final Context context = this;
        //noinspection SimplifiableIfStatement
        if (id == R.id.settings) {
            intent = new Intent(context, MainActivity.class);
            startActivity(intent);
            return true;
        }
        else if(id == R.id.sensor){

            return true;
        }
        else if(id == R.id.data){
            intent = new Intent(context, data.class);
            startActivity(intent);
            return true;
        }
        else if(id == R.id.graph){
            intent = new Intent(context, graph.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
