package com.example.sensorapp;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    SensorManager sensorManager;
    Sensor accelerometer;
    Sensor lightSensor;
    Sensor proximitySensor;

    // Separate X Y Z for accelerometer
    TextView tvAccelX, tvAccelY, tvAccelZ;
    TextView tvLight;
    TextView tvProximity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Connect views
        tvAccelX    = findViewById(R.id.tvAccelX);
        tvAccelY    = findViewById(R.id.tvAccelY);
        tvAccelZ    = findViewById(R.id.tvAccelZ);
        tvLight     = findViewById(R.id.tvLight);
        tvProximity = findViewById(R.id.tvProximity);

        // Get sensor manager
        sensorManager   = (SensorManager) getSystemService(SENSOR_SERVICE);

        // Get sensors
        accelerometer   = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        lightSensor     = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        proximitySensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, accelerometer,   SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this, lightSensor,     SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this, proximitySensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            tvAccelX.setText(event.values[0] + " m/s²");
            tvAccelY.setText(event.values[1] + " m/s²");
            tvAccelZ.setText(event.values[2] + " m/s²");
        }

        if (event.sensor.getType() == Sensor.TYPE_LIGHT) {
            tvLight.setText(event.values[0] + " lux");
        }

        if (event.sensor.getType() == Sensor.TYPE_PROXIMITY) {
            tvProximity.setText(event.values[0] + " cm");
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // leave empty
    }
}
