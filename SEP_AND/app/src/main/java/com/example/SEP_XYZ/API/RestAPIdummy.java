package com.example.SEP_XYZ.API;

import com.example.SEP_XYZ.models.Measurmeant;
import com.example.SEP_XYZ.models.RoomList;
import com.google.gson.Gson;

import static java.lang.Thread.sleep;

public class RestAPIdummy implements Runnable {

    private final Measurmeant M0 = new Measurmeant(370,46,20);
    private final Measurmeant M1 = new Measurmeant(380,47,20);
    private final Measurmeant M2 = new Measurmeant(360,48,21);
    private final Measurmeant M3 = new Measurmeant(355,47,20);
    private final Measurmeant M4 = new Measurmeant(400,49,21);
    private final Measurmeant M5 = new Measurmeant(430,50,22);
    private final Measurmeant M6 = new Measurmeant(445,49,22);
    private final Measurmeant M7 = new Measurmeant(480,51,21);
    private final Measurmeant M8 = new Measurmeant(500,53,22);
    private final Measurmeant M9 = new Measurmeant(600,55,20);

    private final Measurmeant[] measurmeants={M0,M1,M2,M3,M4,M5,M6,M7,M8,M9};

    private int count=0;

    private String send=new String();

    private static RestAPIdummy instance;



    public static RestAPIdummy getInstance(){
        if(instance==null)
            instance=new RestAPIdummy();
        return instance;

    }



    @Override
    public void run() {
        try {
            Gson gson = new Gson();
            String json = gson.toJson(measurmeants[count]);
            sleep(5000);
            increment();
            send = json;

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
    public String sendJson()
    {
        this.run();
        return send;
    }

    private void increment()
    {
        if(count<10)
            count++;
        count=0;
    }
    public String getJson()
    {
        return send;
    }
}
