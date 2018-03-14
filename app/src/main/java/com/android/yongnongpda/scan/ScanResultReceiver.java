package com.android.yongnongpda.scan;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;

public class ScanResultReceiver extends BroadcastReceiver {
	public static final int READ_RESULT = 999;

	Handler mHandler = null;

	public ScanResultReceiver(Handler handler) {
		this.mHandler = handler;
	}

	@Override
	public void onReceive(Context context, Intent intent) {
		if (intent.getAction().equals(Scanner.RES_ACTION)) {
			final String scanResult = intent.getStringExtra("value");
			mHandler.sendMessage(Message.obtain(mHandler, READ_RESULT, scanResult));
		} else {
			mHandler.sendMessage(Message.obtain(mHandler, READ_RESULT, ""));
		}
	}
}
