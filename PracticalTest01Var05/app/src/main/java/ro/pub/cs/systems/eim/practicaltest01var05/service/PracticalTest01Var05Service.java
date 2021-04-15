package ro.pub.cs.systems.eim.practicaltest01var05.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;

import java.util.ArrayList;

import ro.pub.cs.systems.eim.practicaltest01var05.general.Constants;


public class PracticalTest01Var05Service extends Service {
    private ProcessingThread processingThread;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        processingThread.stopThread();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        ArrayList<String> sablon = intent.getStringArrayListExtra(Constants.SABLON);
        processingThread = new ProcessingThread(this, sablon);
        processingThread.start();
        return Service.START_REDELIVER_INTENT;
    }
}
