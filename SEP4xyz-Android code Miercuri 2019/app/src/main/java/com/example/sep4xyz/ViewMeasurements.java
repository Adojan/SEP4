package com.example.sep4xyz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ViewMeasurements extends AppCompatActivity {
    TextView CO2;
    TextView humidity;
    TextView temperature;
    Button getValues;
    Measurements measurements= new Measurements();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_data);
        CO2= findViewById(R.id.CO2);
        humidity=findViewById(R.id.Humidity);
        temperature= findViewById(R.id.Temperature);
        getValues= findViewById(R.id.getV);


    }
    public void getValues(View view){
        CO2.setText(measurements.getCO2()+"");
        humidity.setText(measurements.getHumidity()+"");
        temperature.setText(measurements.getTemperature()+"");
        finish();



    }

}
