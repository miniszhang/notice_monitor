package com.apicloud.notice;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.os.Bundle;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.text.format.DateFormat;

@SuppressLint("SimpleDateFormat")
public class Noticeserver extends NotificationListenerService{
	 // 在收到消息时触发
    @Override
    public void onNotificationPosted(StatusBarNotification sbn) {
        // TODO Auto-generated method stub
        Bundle extras = sbn.getNotification().extras;
        
        int id = sbn.getId(); 
        String title = extras.getString(Notification.EXTRA_TITLE);
        String packgename = sbn.getPackageName();
        CharSequence time = DateFormat.format("yyyy-MM-dd HH:mm:ss", sbn.getPostTime()); //formatter.format(curDate);  
        String content = extras.getString(Notification.EXTRA_TEXT);
        
        String res = "{\"type\":\"add\",\"title\":\""+ title +"\",\"packgename\":\""+ packgename +"\",\"time\":\""+ time +"\",\"content\":\""+ content +"\",\"id\":\""+ id +"\"}";
        Public.saveFile( res,"notice.txt");
//        Log.i("XSL_Test", res);
    }
    
    // 在删除消息时触发
    @Override
    public void onNotificationRemoved(StatusBarNotification sbn) {
        // TODO Auto-generated method stub
        Bundle extras = sbn.getNotification().extras;
        
        int id = sbn.getId(); 
        String title = extras.getString(Notification.EXTRA_TITLE);
        String packgename = sbn.getPackageName();
        CharSequence time = DateFormat.format("yyyy-MM-dd HH:mm:ss", sbn.getPostTime()); //formatter.format(curDate);  
        String content = extras.getString(Notification.EXTRA_TEXT);
        
        String res = "{\"type\":\"remove\",\"title\":\""+ title +"\",\"packgename\":\""+ packgename +"\",\"time\":\""+ time +"\",\"content\":\""+ content +"\",\"id\":\""+ id +"\"}";
        Public.saveFile( res,"notice.txt");
//        Log.i("XSL_Test", res);
    }
}
