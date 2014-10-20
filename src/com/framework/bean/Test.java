package com.framework.bean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

//���������ޣ���ʹ��������Ϊ������
@DatabaseTable(tableName = "test")
public class Test {
	// ��������
	@DatabaseField(useGetSet = true)
	private int state;
	// ����
	@DatabaseField(id = true)
	private int userprofileId;

	public Test(int state, int userprofileId) {
		this.state = state;
		this.userprofileId = userprofileId;
	}

	// ��������޲εĹ���
	public Test() {
	}

	@Override
	public String toString() {
		return "Test [state=" + state + ", userprofileId=" + userprofileId + "]";
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public int getUserprofileId() {
		return userprofileId;
	}

	public void setUserprofileId(int userprofileId) {
		this.userprofileId = userprofileId;
	}

}
