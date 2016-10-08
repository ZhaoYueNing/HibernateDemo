package cn.zhaoyuening.demo;


import cn.zhaoyuening.model.Student;
import cn.zhaoyuening.utils.SessionManager;
import cn.zhaoyuening.utils.TransactionExcuter;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

/**
 * Created by buynow on 16-10-4.
 */
public class Demo_loadMode {
    @Test
    public void test_loadget(){
        SessionManager sm = new SessionManager();
        Session session = sm.getSession();
        Transaction transaction = session.beginTransaction();

        Student s1 = (Student) session.load(Student.class, 1);
        System.out.println(s1.getName());

        transaction.commit();
        System.out.println(s1.getName());
        System.out.println(s1);
    }

}
