package com.framework.service;

import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;

import com.framework.receiver.OpenLockReceiver;

public class OpenLockService extends Service {
	OpenLockReceiver receiver = null;

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		IntentFilter filter = new IntentFilter();
		filter.addAction(Intent.ACTION_SCREEN_ON);
		filter.addAction(Intent.ACTION_SCREEN_OFF);
		receiver = new OpenLockReceiver();
		registerReceiver(receiver, filter);
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// // ��ֹservice��kill
		// // ����һ������serviceǰ̨����
		// Notification notification = new Notification(R.drawable.ic_launcher,
		// "test", System.currentTimeMillis());
		// PendingIntent pendingintent = PendingIntent.getActivity(this, 0, new
		// Intent(this, SecendActivity_.class), 0);
		// notification.setLatestEventInfo(this, "uploadservice", "�뱣�ֳ����ں�̨����",
		// pendingintent);
		// startForeground(0x111, notification);
		// // �����������÷���ֵ
		// flags = START_STICKY;
		// // ����������ondestory�����У��Զ�����service
		// // ����д��ondestory�����С�������
		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public void onDestroy() {
		System.out.println("service onDestroy");

		unregisterReceiver(receiver);
		stopForeground(true);
		// // ��ֹservice��ɱ��������
		// if (startIntent != null) {
		//
		// System.out.println("serviceIntent not null");
		//
		// startService(startIntent);
		//
		// }
	}

}
