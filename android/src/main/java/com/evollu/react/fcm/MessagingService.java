package com.evollu.react.fcm;

import android.content.Intent;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.lang.Object;

public class MessagingService extends FirebaseMessagingService {

    private static final String TAG = "MessagingService";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Log.d(TAG, "Remote message received: " + remoteMessage.getData().toString());

        Map data = remoteMessage.getData();
        Boolean update_available = (data.get("update_available") != null && Boolean.valueOf(data.get("update_available").toString()));
        Log.d(TAG, "update_available: " + update_available);
        Intent intent = null;
        if(update_available) {
          Log.d(TAG, "sending update_available");
          intent = new Intent();
          intent.setAction("com.affinityreact.action.UPDATE_APP");
        } else {
          Log.d(TAG, "sending ReceiveNotification");
          intent = new Intent("com.evollu.react.fcm.ReceiveNotification");
          intent.putExtra("data", remoteMessage);
        }
        sendOrderedBroadcast(intent, null);
    }
}
