package visual_environments.ee408finalproject;

import android.content.Context;
import android.content.Intent;
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

        llContainer = (LinearLayout) findViewById(R.id.ll_Container);
        for (int i=0;i<numSensor;i++){
            View childLayout = getLayoutInflater().inflate(R.layout.sensor_display, null);
            childLayout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            childLayout.setId(i);

            llContainer.addView(childLayout);

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
