package com.framework.util;

import java.sql.SQLException;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

import com.framework.bean.Test;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

public class OrmDatabaseHelper extends OrmLiteSqliteOpenHelper {

	private final static String DATABASE_NAME = "GETUPDB";
	// �����������ݿ��ṹ��һ����Ҫ�������ݿ�汾
	private static int DATABASE_VERSION = 1;

	public OrmDatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	/**
	 * This is called when the database is first created. Usually you should
	 * call createTable statements here to create the tables that will store
	 * your data.
	 */
	@Override
	public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
		try {
			System.out.println("=====create table....");
			TableUtils.createTableIfNotExists(connectionSource, Test.class);
		} catch (SQLException e) {
			System.out.println("=====create table failed....");
			e.printStackTrace();
		}

	}

	/**
	 * This is called when your application is upgraded and it has a higher
	 * version number. This allows you to adjust the various data to match the
	 * new version number.
	 */
	@Override
	public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
		// ���µ���2�汾(newVersion>1)
		if (oldVersion <= 1) {
			// ��database���в���
			database.execSQL("");
		}
		// ���µ���3�汾(newVersion>2)
		if (oldVersion <= 2) {
			// ��database���в���
			database.execSQL("");
		}
		// ...
	}

}
