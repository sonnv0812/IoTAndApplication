package com.example.projectiot.ui.main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;

import com.example.projectiot.R;
import com.example.projectiot.data.model.DataPoint;
import com.example.projectiot.data.model.DataSensor;
import com.example.projectiot.data.repository.main.MainRepository;
import com.example.projectiot.data.repository.main.MainRepositoryImpl;
import com.example.projectiot.service.AlarmMusic;
import com.example.projectiot.ui.login.LoginActivity;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements MainContract.View {

    private MainContract.Presenter presenter;
    private LineChart chartCoLpgSmoke, chartHumidity, chartTemperature;
    private long startTime;
    private FirebaseAuth.AuthStateListener authListener;
    private FirebaseAuth auth;
    float co = 0, lpg = 0, smoke = 0, humidity = 0, temperature = 0;
    Intent intent;
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

        auth = FirebaseAuth.getInstance();

        authListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user == null) {
                    Log.v("MAIN", "TAO DANG O DAY");
                    // user auth state is changed - user is null
                    // launch login activity
                    startActivity(new Intent(MainActivity.this, LoginActivity.class));
                    finish();
                }
            }
        };

    }

    private void initPresenter() {
        MainRepository repository = new MainRepositoryImpl();
        presenter = new MainPresenter(this, repository);
    }

    @Override
    protected void onStart() {
        super.onStart();
        auth.addAuthStateListener(authListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (authListener != null) {
            auth.removeAuthStateListener(authListener);
        }
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

        co = data.getCO();
        lpg = data.getLPG();
        smoke = data.getSmoke();
        humidity = data.getHumidity();
        temperature = data.getTemperature();

        dataCo.add(new Entry(pointCo.getxValue(), pointCo.getyValue()));
        dataLpg.add(new Entry(pointLpg.getxValue(), pointLpg.getyValue()));
        dataSmoke.add(new Entry(pointSmoke.getxValue(), pointSmoke.getyValue()));
        dataHumidity.add(new Entry(pointHumidity.getxValue(), pointHumidity.getyValue()));
        dataTemperature.add(new Entry(pointTemperature.getxValue(), pointTemperature.getyValue()));

        LineDataSet setCo, setLpg, setSmoke, setHumidity, setTemperature;
        setCo = new LineDataSet(dataCo, "CO");
        setCo.setCircleRadius(1f);

        setLpg = new LineDataSet(dataLpg, "LPG");
        setLpg.setColor(Color.RED);
        setLpg.setCircleRadius(1f);

        setSmoke = new LineDataSet(dataSmoke, "SMOKE");
        setSmoke.setCircleRadius(1f);
        setSmoke.setColor(Color.GREEN);

        setHumidity = new LineDataSet(dataHumidity, "HUMIDITY");
        setHumidity.setColor(Color.BLACK);
        setHumidity.setCircleRadius(1f);

        setTemperature = new LineDataSet(dataTemperature, "TEMPERATURE");
        setTemperature.setColor(Color.rgb(255, 166, 0));
        setTemperature.setCircleRadius(1f);

        LineData lineCoLpgSmoke = new LineData(setCo, setLpg, setSmoke);

        chartCoLpgSmoke.setData(lineCoLpgSmoke);
        chartCoLpgSmoke.invalidate();

        LineData lineHumidity = new LineData(setHumidity);
        chartHumidity.setData(lineHumidity);
        chartHumidity.invalidate();

        LineData lineTemperature = new LineData(setTemperature);
        chartTemperature.setData(lineTemperature);
        chartTemperature.invalidate();
        intent = new Intent(MainActivity.this, AlarmMusic.class);
        intent.putExtra("co", co);
        intent.putExtra("lpg", lpg);
        intent.putExtra("smoke", smoke);
        intent.putExtra("humidity", humidity);
        intent.putExtra("temperature", temperature);
        startService(intent);
    }

}
