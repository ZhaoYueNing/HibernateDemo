package cn.zhaoyuening.utils;

import java.io.File;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class SessionManager {
	private static ServiceRegistry registry;
	private static Configuration config ;
	private static SessionFactory sessionFactory;
	private Session session;
	
	static {
		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
			//当实例被虚拟机回收时 关闭所有资源
			@Override
			public void run() {
				SessionManager.closeSessionFactory();
			}
		}));
	}
	
	public SessionManager(){
		config = new Configuration().configure();
		registry = new StandardServiceRegistryBuilder().applySettings(config.getProperties()).build();		
	}
	public SessionManager(String resource){
		config = new Configuration().configure(resource);
		registry = new StandardServiceRegistryBuilder().applySettings(config.getProperties()).build();
	}
	public SessionManager(File file){
		config = new Configuration().configure(file);
		registry = new StandardServiceRegistryBuilder().applySettings(config.getProperties()).build();
	}
	
	
	/**
	 * 获取一个sessionFactory对象
	 * sessionFactory为单例
	 * @return SessionFactory对象
	 */
	public SessionFactory getSessionFactory(){
		if (sessionFactory==null||sessionFactory.isClosed()) {
			sessionFactory = config.buildSessionFactory(registry);
		}
		return sessionFactory;
	}
	/**
	 * 得到一个session对象
	 * 当事物关闭时将会自动关闭并且删除
	 * @return Session object
	 */
	public Session getSession(){
		return getSessionFactory().getCurrentSession();
	}
	
	/**
	 * 开启事物
	 */
	public Transaction beginTransaction(){
		Transaction transaction = getSession().beginTransaction();
		return transaction;
	}
	
	/**
	 * 关闭单例SessionFactory对象
	 * 下次再次调用getSessionFactory时将创建一个新的实例
	 */
	public static void closeSessionFactory(){
		if (sessionFactory!=null&&!sessionFactory.isClosed()) {
			sessionFactory.close();
			SessionManager.sessionFactory = null;
		}
	}
	
	
	
}
