package com.android.yongnongpda.application;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Vibrator;
import android.util.SparseIntArray;

import com.android.yongnongpda.R;
import com.android.yongnongpda.confing.AppConfig;
import com.winsafe.mylibrary.scan.Scanner;
import com.winsafe.mylibrary.utils.SharedManager;
import com.winsafe.mylibrary.utils.UnknownException;

import org.litepal.LitePal;
import org.litepal.LitePalApplication;


public class MyApp extends LitePalApplication {

	public static MyApp myApp;
	public static SharedManager shared;
	public static SoundPool mSoundPool;
	public static Vibrator mVibrator;
	private static SparseIntArray mSparseIntArray;
	//okhttp 返回数据
	public static String DATAJSONObject = "";

	@Override
	public void onCreate() {
		super.onCreate();
		init();

	}

	private void init() {
		myApp = this;
		if (shared == null) {
			shared = new SharedManager(this, AppConfig.SHARED_NAME);
		}
		initSound();
		Scanner.init(this);//初始化
		LitePal.getDatabase();//创建数据库
		crashUnknownException();
	}

	//获取异常日志
	private void crashUnknownException() {
		UnknownException mUnknownException = UnknownException.getInstance();
		mUnknownException.init(getApplicationContext(), AppConfig.isLogSave);
	}

	public static void initSound() {
		if (mSoundPool == null)
			mSoundPool = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
		mSparseIntArray = new SparseIntArray();
		mSparseIntArray.append(0, mSoundPool.load(myApp, R.raw.beep, 1));
		mSparseIntArray.append(1, mSoundPool.load(myApp, R.raw.error, 1));
	}

	public static void playSound(int pos) {
		if (mSoundPool != null) {
			mSoundPool.play(mSparseIntArray.get(pos), 1, 1, 1, 0, 1f);
			if (pos == 1)
				startVibrate();
		}
	}

	public static void startVibrate() {
		if (mVibrator == null)
			mVibrator = (Vibrator) myApp.getSystemService(Context.VIBRATOR_SERVICE);
		mVibrator.vibrate(300);
	}


}
