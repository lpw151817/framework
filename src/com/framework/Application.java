package com.framework;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.acra.ACRA;
import org.acra.ErrorReporter;
import org.acra.ReportField;
import org.acra.ReportingInteractionMode;
import org.acra.annotation.ReportsCrashes;
import org.acra.collector.CrashReportData;
import org.acra.sender.EmailIntentSender;
import org.acra.sender.GoogleFormSender;
import org.acra.sender.HttpSender;
import org.acra.sender.HttpSender.Method;
import org.acra.sender.HttpSender.Type;
import org.acra.sender.ReportSender;
import org.acra.sender.ReportSenderException;

import android.content.Context;
import android.widget.Toast;

import com.framework.util.Contants;
import com.framework.util.VolleyTools;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@ReportsCrashes(formKey = "",// ����crashReport��google�˻��У�Ĭ��Ϊ��
// = "dGVacG0ydVHnaNHjRjVTUTEtb3FPWGc6MQ",
// mailTo = "user@domain.com",// EmailIntentSender���͵�Ŀ��emial
// formUri = "",//HttpSenderĬ�Ϸ��͵ķ�����uri��ַ
customReportContent = { ReportField.APP_VERSION_NAME, ReportField.APP_VERSION_CODE,
		ReportField.ANDROID_VERSION, ReportField.PHONE_MODEL, ReportField.STACK_TRACE,
		ReportField.LOGCAT, ReportField.THREAD_DETAILS, ReportField.USER_CRASH_DATE }, mode = ReportingInteractionMode.SILENT)
public class Application extends android.app.Application {
	private static VolleyTools tools;
	private static Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

	@Override
	public void onCreate() {
		super.onCreate();
		tools = new VolleyTools(this);
		ACRA.init(this);
		ErrorReporter.getInstance().removeAllReportSenders();
		ErrorReporter.getInstance().setReportSender(new CrashReportSender(this));
	}

	public static VolleyTools getTools() {
		return tools;
	}

	@Override
	public void onTerminate() {
		super.onTerminate();
		tools.release();
	}

	public static Gson getGson() {
		return gson;
	}

	private class CrashReportSender implements ReportSender {

		private Context context;

		public CrashReportSender(Context ctx) {
			this.context = ctx;
		}

		@Override
		public void send(CrashReportData arg0) throws ReportSenderException {

			// // ����email����
			// System.out.println("����email");
			// new EmailIntentSender(context).send(arg0);

			// // ����post����formuri
			// System.out.println("����post����formuri");
			// Map<ReportField, String> params = new HashMap<ReportField,
			// String>();
			// params.put(ReportField.ANDROID_VERSION,
			// arg0.getProperty(ReportField.ANDROID_VERSION));
			// params.put(ReportField.APP_VERSION_NAME,
			// arg0.getProperty(ReportField.APP_VERSION_NAME));
			// params.put(ReportField.APP_VERSION_CODE,
			// arg0.getProperty(ReportField.APP_VERSION_CODE));
			// params.put(ReportField.PHONE_MODEL,
			// arg0.getProperty(ReportField.PHONE_MODEL));
			// params.put(ReportField.STACK_TRACE,
			// arg0.getProperty(ReportField.STACK_TRACE));
			// params.put(ReportField.LOGCAT,
			// arg0.getProperty(ReportField.LOGCAT));
			// params.put(ReportField.THREAD_DETAILS,
			// arg0.getProperty(ReportField.THREAD_DETAILS));
			// params.put(ReportField.USER_CRASH_DATE,
			// arg0.getProperty(ReportField.USER_CRASH_DATE));
			// new HttpSender(Method.POST, Type.JSON, params);

			// // ����CrashReport��google�˻�
			// System.out.println("����CrashReport��google�˻�");
			// new GoogleFormSender().send(arg0);

			// ���汾��log
			String fileName = "CrashReport_"
					+ new SimpleDateFormat("yyyy-MMddHH-mmss").format(new Date(System
							.currentTimeMillis())) + ".txt";
			File logFile = new File(Contants.crash_log_path_without_filename, fileName);

			try {
				// ��Ŀ¼�Ƿ����
				if (!logFile.getParentFile().exists())
					logFile.getParentFile().mkdirs();
				// �ļ��Ƿ����
				if (!logFile.exists())
					logFile.createNewFile();

				// д������
				FileWriter filerWriter = new FileWriter(logFile, true);
				BufferedWriter bufWriter = new BufferedWriter(filerWriter);
				bufWriter.write("ReportField.ANDROID_VERSION = "
						+ arg0.getProperty(ReportField.ANDROID_VERSION));
				bufWriter.newLine();
				bufWriter.write("ReportField.APP_VERSION_NAME = "
						+ arg0.getProperty(ReportField.APP_VERSION_NAME));
				bufWriter.newLine();
				bufWriter.write("ReportField.APP_VERSION_CODE = "
						+ arg0.getProperty(ReportField.APP_VERSION_CODE));
				bufWriter.newLine();
				bufWriter.write("ReportField.ANDROID_VERSION = "
						+ arg0.getProperty(ReportField.ANDROID_VERSION));
				bufWriter.newLine();
				bufWriter
						.write("ReportField.PHONE_MODEL = " + arg0.getProperty(ReportField.PHONE_MODEL));
				bufWriter.newLine();
				bufWriter.write("ReportField.USER_CRASH_DATE = "
						+ arg0.getProperty(ReportField.USER_CRASH_DATE));
				bufWriter.newLine();
				bufWriter.write("============= ReportField.THREAD_DETAILS ================");
				bufWriter.newLine();
				bufWriter.write("ReportField.THREAD_DETAILS = "
						+ arg0.getProperty(ReportField.THREAD_DETAILS));
				bufWriter.newLine();
				bufWriter.write("============= ReportField.STACK_TRACE ================");
				bufWriter.newLine();
				bufWriter
						.write("ReportField.STACK_TRACE = " + arg0.getProperty(ReportField.STACK_TRACE));
				bufWriter.newLine();
				bufWriter.write("============= ReportField.LOGCAT ================");
				bufWriter.newLine();
				bufWriter.write("ReportField.LOGCAT = " + arg0.getProperty(ReportField.LOGCAT));
				bufWriter.newLine();

				bufWriter.close();
				filerWriter.close();

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
