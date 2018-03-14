package com.android.yongnongpda.scan;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.os.Handler;


public class Scanner {
	private static Context mContext;

	public static final String RES_ACTION = "android.intent.action.SCANRESULT";

	private static ScannerInterface mScanner;

	private static BroadcastReceiver mBroadcastReceiver;
	private static IntentFilter mIntentFilter;

	public static Handler mHandler;

	public static void init(Context context) {
		mContext = context;

		mScanner = new ScannerInterface(context);
		mScanner.open(); // 打开扫描头上电
//		mScanner.close();//打开扫描头下电
		mScanner.enablePlayBeep(false);// 是否允许蜂鸣反馈
//		mScanner.enableFailurePlayBeep(false);// 扫描失败蜂鸣反馈
		mScanner.enablePlayVibrate(false);// 是否允许震动反馈
//		mScanner.enableAddKeyValue(1);/** 附加无、回车、Teble、换行 */
//		mScanner.timeOutSet(2);//设置扫描延时2秒
//		mScanner.intervalSet(1000); //设置连续扫描间隔时间
//		mScanner.lightSet(false);//关闭右上角扫描指示灯
//		mScanner.enablePower(true);//省电模式
//		mScanner.addPrefix("AAA");// 添加前缀
//		mScanner.addSuffix("BBB");// 添加后缀
//		mScanner.interceptTrimleft(2); // 截取条码左边字符
//		mScanner.interceptTrimright(3);// 截取条码右边字符
//		mScanner.filterCharacter("R");// 过滤特定字符
//		mScanner.SetErrorBroadCast(true);// 扫描错误换行
//		mScanner.resultScan();// 恢复iScan默认设置
//		mScanner.lockScanKey();// 锁定设备的扫描按键,通过iScan定义扫描键扫描，用户也可以自定义按键。
		mScanner.unlockScanKey();// 释放扫描按键的锁定，释放后iScan无法控制扫描按键，用户可自定义按键扫描。

		/* 设置扫描结果的输出模式:
		 * 参数0为模拟输出（在光标停留的地方输出扫描结果）；
		 * 参数1为广播输出（由应用程序编写广播接收者来获得扫描结果，并在指定的控件上显示扫描结果） */
		mScanner.setOutputMode(1);

		/* 初始化意图过滤器：
		 * 扫描结果的意图过滤器的动作一定要使用"android.intent.action.SCANRESULT" */
		mIntentFilter = new IntentFilter(RES_ACTION);
	}

	public static void registerBroadcastReceiver() {
		mBroadcastReceiver = new ScanResultReceiver(mHandler);
		mContext.registerReceiver(mBroadcastReceiver, mIntentFilter);
	}

	public static void unregisterBroadcastReceiver() {
		mScanner.lockScanKey(); // 退出的时候还是要让那些个按键能够触发扫描
		mContext.unregisterReceiver(mBroadcastReceiver);
	}

	public static void startScanning() {
		mScanner.scan_start();
	}

	public static void stopScanning() {
		mScanner.scan_stop();
	}
}
