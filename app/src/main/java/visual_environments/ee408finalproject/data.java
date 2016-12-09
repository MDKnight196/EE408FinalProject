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
import android.widget.TextView;

import java.text.DecimalFormat;

public class data extends AppCompatActivity {
    private  TextView tvThetaHat, tvThetaObver, tvYVal, tvNumSensor, tvPow, tvNVar, tvVVar, tvA, tvC;
    private DecimalFormat two = new DecimalFormat("#.##");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        tvThetaHat= (TextView) findViewById(R.id.tv_ThetaHat);
        tvThetaObver = (TextView) findViewById(R.id.tv_ThetaObser);
        tvYVal = (TextView) findViewById(R.id.tv_YVal);
        tvNumSensor = (TextView) findViewById(R.id.numSensor);
        tvPow = (TextView) findViewById(R.id.tv_Pow);
        tvVVar = (TextView) findViewById(R.id.tv_V_Var);
        tvNVar = (TextView) findViewById(R.id.tv_N_var);
        tvA = (TextView) findViewById(R.id.tv_A);
        tvC = (TextView) findViewById(R.id.tv_C);

        tvThetaHat.setText("Theta Hat: "+two.format(SimulationManager.getLastSimulation().getThetaHat().getReal()));
        tvYVal.setText("Y Value: "+SimulationManager.getLastSimulation().getYVal().toFormattedString());
        tvThetaObver.setText("Observation with theta: " +two.format(SimulationManager.getSimulationSetup().getTheta()));
        tvNumSensor.setText("Number of sensors to use: "+SimulationManager.getSimulationSetup().getSensorCount());
        tvPow.setText("Power Value: "+two.format(SimulationManager.getSimulationSetup().getPower()));
        tvNVar.setText("N-Variance: "+two.format(SimulationManager.getSimulationSetup().getVarianceN()));
        tvVVar.setText("V-Variance: "+two.format(SimulationManager.getSimulationSetup().getVarianceV()));
        tvA.setText("Alpha Type: "+(SimulationManager.getSimulationSetup().isUniform() ? "Uniform" :
                "Optimum"));
        tvC.setText("Channel Type: "+(SimulationManager.getSimulationSetup().isRician() ? "Rician" :
                "AWGN"));
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
            intent = new Intent(context, Sensor_page.class);
            startActivity(intent);
            return true;
        }
        else if(id == R.id.data){
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
