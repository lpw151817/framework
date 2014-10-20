package com.framework.activity;


import com.framework.Application;
import com.framework.dao.BaseDAO;
import com.framework.util.VolleyTools;
import com.google.gson.Gson;

import android.app.Activity;

public class BaseActivity extends Activity {
	// ���ڱ������ݿ�洢
	protected BaseDAO dao;

	@Override
	protected void onStop() {
		super.onStop();
		getVolleyTools().getQueue().cancelAll(this);
	}

	public VolleyTools getVolleyTools() {
		return Application.getTools();
	}

	public Gson getGson() {
		return Application.getGson();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		// �ͷŵ�dao
		if (dao != null) {
			dao.release();// �ͷ�dao�е�databasehelper
			dao = null;
		}

	}
}
