package com.example.projectiot.ui.main;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;

import com.example.projectiot.R;
import com.example.projectiot.data.model.DataPoint;
import com.example.projectiot.data.model.DataSensor;
import com.example.projectiot.data.repository.main.MainRepository;
import com.example.projectiot.data.repository.main.MainRepositoryImpl;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements MainContract.View {

    private MainContract.Presenter presenter;
    private LineChart chartCoLpgSmoke, chartHumidity, chartTemperature;
    private long startTime;
    ArrayList<Entry> dataCo = new ArrayList<Entry>();
    ArrayList<Entry> dataLpg = new ArrayList<Entry>();
    ArrayList<Entry> dataSmoke = new ArrayList<Entry>();
    ArrayList<Entry> dataHumidity = new ArrayList<Entry>();
    ArrayList<Entry> dataTemperature = new ArrayList<Entry>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initPresenter();

        chartCoLpgSmoke = findViewById(R.id.chart_co_lpg_smoke);
        chartHumidity = findViewById(R.id.chart_humidity);
        chartTemperature = findViewById(R.id.chart_temperature);

        startTime = System.currentTimeMillis();

        chartCoLpgSmoke.getDescription().setText("Biểu đồ khí CO, khí gas, khói");
        chartHumidity.getDescription().setText("Biểu đồ độ ẩm không khí");
        chartTemperature.getDescription().setText("Biểu đồ nhiệt độ");
    }

    private void initPresenter() {
        MainRepository repository = new MainRepositoryImpl();
        presenter = new MainPresenter(this, repository);
    }

    @Override
    protected void onResume() {
        super.onResume();
        new Thread(new Runnable() {
            @Override
            public void run() {
                presenter.readRealtimeData();
            }
        }).start();
    }

    @Override
    public void showMessage(String msg) {

    }

    @Override
    public void updateData(DataSensor data) {
        long time = System.currentTimeMillis();
        DataPoint pointCo = new DataPoint((int) (time - startTime)/1000, (int) data.getCO());
        DataPoint pointLpg = new DataPoint((int) (time - startTime)/1000, (int) data.getLPG());
        DataPoint pointSmoke = new DataPoint((int) (time - startTime)/1000, (int) data.getSmoke());
        DataPoint pointHumidity = new DataPoint((int) (time - startTime)/1000, (int) data.getHumidity());
        DataPoint pointTemperature = new DataPoint((int) (time - startTime)/1000, (int) data.getTemperature());

        Log.v("MAIN", time + " " + startTime);
        dataCo.add(new Entry(pointCo.getxValue(), pointCo.getyValue()));
        dataLpg.add(new Entry(pointLpg.getxValue(), pointLpg.getyValue()));
        dataSmoke.add(new Entry(pointSmoke.getxValue(), pointSmoke.getyValue()));
        dataHumidity.add(new Entry(pointHumidity.getxValue(), pointHumidity.getyValue()));
        dataTemperature.add(new Entry(pointTemperature.getxValue(), pointTemperature.getyValue()));

        LineDataSet setCo, setLpg, setSmoke, setHumidity, setTemperature;
        setCo = new LineDataSet(dataCo, "CO");
        setLpg = new LineDataSet(dataLpg, "LPG");
        setLpg.setColor(Color.RED);
        setSmoke = new LineDataSet(dataSmoke, "SMOKE");
        setSmoke.setColor(Color.GREEN);
        setHumidity = new LineDataSet(dataHumidity, "HUMIDITY");
        setHumidity.setColor(Color.BLACK);
        setTemperature = new LineDataSet(dataTemperature, "TEMPERATURE");
        setTemperature.setColor(Color.rgb(255, 166, 0));

        LineData lineCoLpgSmoke = new LineData(setCo, setLpg, setSmoke);
        chartCoLpgSmoke.setData(lineCoLpgSmoke);
        chartCoLpgSmoke.invalidate();

        LineData lineHumidity = new LineData(setHumidity);
        chartHumidity.setData(lineHumidity);
        chartHumidity.invalidate();

        LineData lineTemperature = new LineData(setTemperature);
        chartTemperature.setData(lineTemperature);
        chartTemperature.invalidate();
    }

}
