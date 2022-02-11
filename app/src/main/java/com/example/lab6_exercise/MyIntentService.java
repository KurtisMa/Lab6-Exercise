package com.example.lab6_exercise;


import android.app.IntentService;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.widget.TextView;
import android.widget.Toast;

public class MyIntentService extends IntentService {
    private static final String ACTION_DEMO = "edu.ucsd.cse110.lab6_services_intent.action.DEMO";
    private static final String GOODBYE_MESSAGE = "edu.ucsd.cse110.lab6_services_intent.extra.GOODBYE_MESSAGE";
    private String goodbyeMessage;

    public MyIntentService() {
        super("MyIntentService");
    }

    public static void startActionDemo(Context context, String goodbyeMessage) {
        Intent intent = new Intent(context, MyIntentService.class);
        intent.setAction(ACTION_DEMO);
        intent.putExtra(GOODBYE_MESSAGE, goodbyeMessage);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (action.equals(ACTION_DEMO)) {
                this.goodbyeMessage = intent.getStringExtra(GOODBYE_MESSAGE);
                handleActionDemo(goodbyeMessage);
            } else {
                //other actions here
            }
        }
    }
    @Override
    public void onDestroy(){
        Toast.makeText(MyIntentService.this,goodbyeMessage, Toast.LENGTH_SHORT).show();
        super.onDestroy();
    }

    private void handleActionDemo(String goodbyeMessage) {
        Toast.makeText(MyIntentService.this, "Service Started", Toast.LENGTH_SHORT).show();

        synchronized (this) {
            try {
                wait(5000); //5 seconds
                SharedPreferences preference = getSharedPreferences("LAB6_EXERCISE_PREFERENCES", MODE_PRIVATE);
                SharedPreferences.Editor editor = preference.edit();
                String name = "";
                editor.putString("name", "Kurtis Ma"); //text is set here
                editor.apply();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        stopSelf();
    }
}
