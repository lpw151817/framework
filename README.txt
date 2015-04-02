1.���е�activity�̳�BaseActivity��Dao�̳�BaseDao��Adapter�̳�com.framework.adapter.BaseAdapter��

2.���ʹ��AndroidAnnotation:
	http://blog.csdn.net/limb99/article/details/9067827
	https://github.com/excilys/androidannotations/wiki
	http://www.itnose.net/detail/6098386.html
	���Ӳ���cm.getuptogether.activity.MainActivity�в��ֵ������Լ��ؼ���������
	

3.�����Activity��ʹ��ormlite������ɾ�Ĳ�
	3.1 �½�ʵ���ࡣÿ��ʵ�����Ӧһ�ű�����cm.getuptogether.bean.Test��
	3.1 �½�ʵ�����dao�ļ����̳�cm.getuptogether.dao.BaseDao����BaseDao�ж����˻�������ɾ�Ĳ�Ĳ����������Ҫ���ӵ���ɾ�Ĳ飬���½���ʵ��������ɼ��ɡ�
	3.2 ��BaseActivity���Ѷ�����dao�������������activity��ֻ��Ҫnewһ����Ӧʵ����Ķ���������ɲ�����Ӧʵ����ı�
	3.3 ��activity������ʵ�����dao�ļ�����sqlite����ɾ�Ĳ�

	System.out.println("===============test insert");
	System.out.println("insert:1 1 ");
	dao.insert(new Test(1, 1));
	System.out.println("insert:2 2");
	dao.insert(new Test(1, 2));

	 System.out.println("===============test query");
	 System.out.println("query state=1");
	 for (Test test : dao.query("state", "1")) {
	 System.out.println(test);
	 }
	 System.out.println("query all");
	 for (Test test : dao.queryAll()) {
	 System.out.println(test);
	 }
	 System.out.println("insert all");
	List<Test> ls = new ArrayList<Test>();
	ls.add(new Test(1, 3));
	ls.add(new Test(1, 4));
	dao.insert(ls);
	 System.out.println("query all");
	 for (Test test : dao.queryAll()) {
	 System.out.println(test);
	 }
	
	 System.out.println("query userprofileId=1,state=1");
	 for (Test test : dao.query(new String[] { "userprofileId",
	 "state" }, new String[] { "1", "1" })) {
	 System.out.println(test);
	 }
	 System.out.println("query userprofileId=2,state=1");
	 for (Test test : dao.query(new String[] { "userprofileId",
	 "state" }, new String[] { "2", "1" })) {
	 System.out.println(test);
	 }
	
	 Map<String, Object> map = new HashMap<String, Object>();
	 map.put("userprofileId", 4);
	 for (Test test : dao.query(map)) {
	 System.out.println(test);
	 }
	
	 Map<String, Object> map = new HashMap<String, Object>();
	 map.put("state", 1);
	 Map<String, Object> low = new HashMap<String, Object>();
	 low.put("userprofileId", 1);
	 Map<String, Object> high = new HashMap<String, Object>();
	 high.put("userprofileId", 4);
	 for (Test test : dao.query(map, low, high)) {
	 System.out.println(test);
	 }

	 System.out.println("===============test update");
	 List<Test> list = dao.queryAll();
	
	 Test test_o = list.get(0);
	
	 test_o.setState(555);
	
	 dao.update(test_o);
	 System.out.println("query all");
	 for (Test test : dao.queryAll()) {
	 System.out.println(test);
	 }

	 System.out.println("======================test delete");
	 System.out.println("query all");
	 for (Test test : dao.queryAll()) {
	 System.out.println(test);
	 }
	 System.out.println("delete state=3");
	 dao.delete("state", "3");
	 for (Test test : dao.queryAll()) {
	 System.out.println(test);
	 }
	 System.out.println("delete state=2 userprofileId=56");
	 dao.delete(new String[]{"state","userprofileId"}, new
	 String[]{"2","56"});
	 for (Test test : dao.queryAll()) {
	 System.out.println(test);
	 }
	 
4.���ʹ��Volley��������ͨ��
	ֱ�Ӵ�BaseActivity���ó�VolleyTools����Ȼ�����cm.getuptogether.activity.MainActivity�С�

5.��BaseActivity�л�������Gson��ֱ�ӾͿ���ʹ�á�


	 