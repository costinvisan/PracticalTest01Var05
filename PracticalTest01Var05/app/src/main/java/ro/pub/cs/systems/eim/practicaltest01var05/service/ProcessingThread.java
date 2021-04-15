package ro.pub.cs.systems.eim.practicaltest01var05.service;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import ro.pub.cs.systems.eim.practicaltest01var05.general.Constants;

public class ProcessingThread extends Thread {
    private Context context = null;
    private boolean isRunning = true;

    private Random random = new Random();

    private ArrayList<String> sablon;

    public ProcessingThread(Context context, ArrayList<String> sablon) {
        this.context = context;
        this.sablon = sablon;
    }

    @Override
    public void run() {
        Log.d(Constants.PROCESSING_THREAD_TAG, "Thread has started! ");
        while (isRunning) {
            sendMessage();
            sleep();
        }
        Log.d(Constants.PROCESSING_THREAD_TAG, "Thread has stopped!");
    }

    private void sendMessage() {
        Intent intent = new Intent();
        intent.setAction(Constants.actionType);
        intent.putExtra(Constants.BROADCAST_RECEIVER_EXTRA, sablon.remove(0));
        context.sendBroadcast(intent);
    }

    private void sleep() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }
    }

    public void stopThread() {
        isRunning = false;
    }
}
