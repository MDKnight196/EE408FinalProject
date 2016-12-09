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
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.text.DecimalFormat;

public class Settings extends AppCompatActivity {
    private EditText etObser, etTheta, etSensor, etPower, etVVal, etNVal, etRician;
    private RadioButton rbOptimum, rbUniform, rbAWGN, rbRician;
    private DecimalFormat two = new DecimalFormat("#.##");
    private RelativeLayout rlContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        etObser = (EditText) findViewById(R.id.et_Obser);
        etTheta = (EditText) findViewById(R.id.et_Theta);
        etPower = (EditText) findViewById(R.id.et_Power);
        etVVal = (EditText) findViewById(R.id.et_VVar);
        etNVal = (EditText) findViewById(R.id.et_NVar);
        etRician = (EditText) findViewById(R.id.et_Rician);
        etSensor = (EditText) findViewById(R.id.et_Sensor);
        rbOptimum = (RadioButton) findViewById(R.id.rb_Optimum);
        rbUniform = (RadioButton) findViewById(R.id.rb_Uniform);
        rbAWGN = (RadioButton) findViewById(R.id.rb_AWGN);
        rbRician = (RadioButton) findViewById(R.id.rb_Rician);


        etObser.setText(SimulationManager.getSimulationSetup().getObservation());
        etTheta.setText(two.format(SimulationManager.getSimulationSetup().getTheta()));
        etSensor.setText(two.format(SimulationManager.getSimulationSetup().getSensorCount()));
        etPower.setText(two.format(SimulationManager.getSimulationSetup().getPower()));
        etVVal.setText(two.format(SimulationManager.getSimulationSetup().getVarianceV()));
        etNVal.setText(two.format(SimulationManager.getSimulationSetup().getVarianceN()));
        if(SimulationManager.getSimulationSetup().isAWGN()){
            rbAWGN.setChecked(true);
            rbRician.setChecked(false);
            rbUniform.setEnabled(true);
            if(SimulationManager.getSimulationSetup().isOptimum()){
                rbUniform.setChecked(false);
                rbOptimum.setChecked(true);
            }
            else {
                rbUniform.setChecked(true);
                rbOptimum.setChecked(false);
            }
            etRician.setText("");
        }
        else{
            rbAWGN.setChecked(false);
            rbRician.setChecked(true);
            rbUniform.setEnabled(false);
            rbOptimum.setChecked(true);
            etRician.setText(two.format(SimulationManager.getSimulationSetup().getK()));
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
            return true;
        } else if (id == R.id.sensor) {
            intent = new Intent(context, Sensor_page.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.data) {
            intent = new Intent(context, data.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.graph) {
            intent = new Intent(context, graph.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
}
    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.rb_AWGN:
                if (checked)
                    etRician.setFocusable(false);
                etRician.setFocusableInTouchMode(false);
                etRician.setClickable(false);
                etRician.setText("");
                rbUniform.setEnabled(true);
                break;
            case R.id.rb_Rician:
                if (checked)
                    etRician.setFocusable(true);
                etRician.setFocusableInTouchMode(true);
                etRician.setClickable(true);
                etRician.setText(two.format(SimulationSetup.DEFAULT_K));

                rbUniform.setEnabled(false);
                rbOptimum.setChecked(true);

                break;
        }
    }

    public void onSimClick(View view){
        RadioGroup rgChannel = (RadioGroup) findViewById(R.id.rg_Channel);
        RadioGroup rgAlpha = (RadioGroup) findViewById(R.id.rg_Alpha);

        Intent intent;
        final Context context = this;
        int idChannel=rgChannel.getCheckedRadioButtonId();

        int idAlpha=rgAlpha.getCheckedRadioButtonId();
        try {
            SimulationManager.getSimulationSetup().setObservation(etObser.getText().toString());
            SimulationManager.getSimulationSetup().setSensorCount(Integer.parseInt(etSensor.getText().toString()));
            SimulationManager.getSimulationSetup().setTheta(Double.parseDouble(etTheta.getText().toString()));
            SimulationManager.getSimulationSetup().setPower(Double.parseDouble(etPower.getText().toString()));
            SimulationManager.getSimulationSetup().setVarianceN(Double.parseDouble(etNVal.getText().toString()));
            SimulationManager.getSimulationSetup().setVarianceV(Double.parseDouble(etVVal.getText().toString()));

            if (idChannel == R.id.rb_Rician) {
                SimulationManager.getSimulationSetup().setK(Double.parseDouble(etRician.getText().toString()));
                SimulationManager.getSimulationSetup().setRician(true);
                SimulationManager.getSimulationSetup().setUniform(false);
            } else if (idChannel == R.id.rb_AWGN) {
                SimulationManager.getSimulationSetup().setRician(false);
                if (idAlpha == R.id.rb_Optimum) {
                    SimulationManager.getSimulationSetup().setUniform(false);
                } else if (idAlpha == R.id.rb_Uniform) {
                    SimulationManager.getSimulationSetup().setUniform(true);
                }
            }

            SimulationManager.runSimulation();

            intent = new Intent(context, Sensor_page.class);
            startActivity(intent);

        }
        catch (Exception e){
            Toast.makeText(getBaseContext(), "Error in settings" , Toast.LENGTH_LONG).show();
        }

    }

    public void onDefaultClick(View view) {
        etObser.setText(SimulationSetup.DEFAULT_OBSERVATION);
        etTheta.setText(two.format(SimulationSetup.DEFAULT_THETA));
        etSensor.setText(two.format(SimulationSetup.DEFAULT_SENSOR_COUNT));
        etPower.setText(two.format(SimulationSetup.DEFAULT_POWER));
        etVVal.setText(two.format(SimulationSetup.DEFAULT_V));
        etNVal.setText(two.format(SimulationSetup.DEFAULT_N));
        rbOptimum.setChecked(true);
        rbUniform.setEnabled(false);
        etRician.setText(two.format(SimulationSetup.DEFAULT_K));
        rbAWGN.setChecked(false);
        rbRician.setChecked(true);

    }



}
