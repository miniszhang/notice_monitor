package com.apicloud.notice;

import java.util.Set;

import org.json.JSONObject;

import com.uzmap.pkg.uzcore.UZWebView;
import com.uzmap.pkg.uzcore.uzmodule.UZModule;
import com.uzmap.pkg.uzcore.uzmodule.UZModuleContext;

import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import android.support.v4.app.NotificationManagerCompat;

public class Mnotice extends UZModule{

	public Mnotice(UZWebView webView) {
		super(webView);
		try {
			Public.deletefile("notice.txt");
			if(!isNotificationListenerEnabled(context())) {
				Intent intent;
		        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP_MR1) {
		            intent = new Intent(Settings.ACTION_NOTIFICATION_LISTENER_SETTINGS);
		        } else {
		            intent = new Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS");
		        }
		        startActivity(intent);
			}
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
	public void jsmethod_notice(final UZModuleContext moduleContext){
		final JSONObject ret = new JSONObject();
		new Thread(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				while(true) {
					try {
						Thread.sleep(1);
						String s = Public.getFile("notice.txt");
						if (s == null || s.length() <= 0) {
						}else {
							ret.put("res", s);
							moduleContext.success(ret, false);
							Public.saveFile("","notice.txt");
						}
					}catch(Exception e) {
						e.printStackTrace();
					}

				}
			}
			 
		 }).start();
	}
	
	//判断监听服务是否开启
	public boolean isNotificationListenerEnabled(Context context) {
	    Set<String> packageNames = NotificationManagerCompat.getEnabledListenerPackages(context);
	    if (packageNames.contains(context.getPackageName())) {
	        return true;
	    }
	    return false;
	}
	
}
