package cn.zhaoyuening.utils;

import org.hibernate.Session;
import org.hibernate.Transaction;

public abstract class TransactionExcuter {
	private Session session;


	public void excute(){
		session = new SessionManager().getSession();
		Transaction t = session.beginTransaction();
		//事物开始
		transaction();
		//事物结束
		t.commit();
		session = null;
	}
	public abstract void transaction();
	public Session getSession() {
		return session;
	}
}
