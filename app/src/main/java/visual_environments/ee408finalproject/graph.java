package visual_environments.ee408finalproject;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;


import com.github.mikephil.charting.charts.ScatterChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.ScatterData;
import com.github.mikephil.charting.data.ScatterDataSet;
import com.github.mikephil.charting.utils.EntryXComparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class graph extends AppCompatActivity {


    //private LineGraphSeries<DataPoint> lineGraphSeries;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Set up the chart.
        ScatterChart chart = (ScatterChart) findViewById(R.id.chart);
        chart.getLegend().setEnabled(false);
        chart.getXAxis().setAxisMinimum((float) -0.4);
        chart.getXAxis().setAxisMaximum((float) 2.6);
        chart.setTouchEnabled(false);

        double mean;
        double variance;
        double [] gaussianSamples;
        double [] samples;
        double [] distrVals;
        List<Entry> entries = new ArrayList<Entry>();
        mean = SimulationManager.getSimulationSetup().getTheta();
        variance = (SimulationManager.getSimulationSetup().getC()/SimulationManager.getSimulationSetup().getSensorCount());
        gaussianSamples = new double[SimulationManager.getSimulationSetup().getSensorCount()];

        Random ran = new Random();
        gaussianSamples = new double[SimulationManager.getSimulationSetup().getSensorCount()];
        for (int i = 0; i < SimulationManager.getSimulationSetup().getSensorCount(); i++)
        {
            gaussianSamples[i] = (ran.nextGaussian());
        }
        distrVals = new double[SimulationManager.getSimulationSetup().getSensorCount()];
        samples = new double[SimulationManager.getSimulationSetup().getSensorCount()];

        for (int j = 0; j < SimulationManager.getSimulationSetup().getSensorCount(); j++) {
            if (j < SimulationManager.getSimulationSetup().getSensorCount() / 2)
                samples[j] = (mean - (Math.sqrt(variance) * gaussianSamples[j]));
            else
                samples[j] = (mean + (Math.sqrt(variance) * gaussianSamples[j]));
            distrVals[j] = (Math.pow(Math.exp(-(((samples[j] - mean) * (samples[j] - mean)) / ((2 * variance)))), 1 / (Math.sqrt(variance) * Math.sqrt(2 * Math.PI))));
        }


        // Put values into the List<Entry> entries.
        for (int k = 0; k < SimulationManager.getSimulationSetup().getSensorCount(); k++)
        {
            entries.add(new Entry((float) ((0+samples[k])-mean+1), (float) distrVals[k]));
        }

        Collections.sort(entries, new EntryXComparator());
        ScatterDataSet distributionData = new ScatterDataSet(entries, "Default Distribution");  // Add entries to dataset
        ScatterData distData = new ScatterData(distributionData);
        distData.setDrawValues(false);
        chart.setData(distData);
        //float[] dataX = new float[SimulationManager.getSimulationSetup().getSensorCount()];
        //float[] dataY = new float[SimulationManager.getSimulationSetup().getSensorCount()];
        //lineGraphSeries = new LineGraphSeries<>();
        //GraphView graph = (GraphView) findViewById(R.id.chart);
        /*
        for (Sensor s : SimulationManager.getLastSimulation().getSensorList()) {
            dataX[s.getID() - 1] = s.getID();
            dataY[s.getID() - 1] = (float) s.getNVal().getReal();
            lineGraphSeries.appendData(new DataPoint(dataX[s.getID() - 1], dataY[s.getID() - 1]), true, 100);
        }
        */
        //graph.addSeries(lineGraphSeries);

        /*
        lineChart = (LineChart) findViewById(R.id.chart);
        entries = new ArrayList<>();
        for (Sensor s : SimulationManager.getLastSimulation().getSensorList()) {
            entries.add(new Entry((s.getID()), (float) s.getNVal().getReal()));
        }
        lineDataSet = new LineDataSet(entries, "Sensors");
        lineChart.setData(lineData);
        lineChart.animateY(3000);
        */

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
            intent = new Intent(context, data.class);
            startActivity(intent);
            return true;
        }
        else if(id == R.id.graph){
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
