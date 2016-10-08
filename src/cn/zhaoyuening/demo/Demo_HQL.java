package cn.zhaoyuening.demo;

import cn.zhaoyuening.model.Classes;
import cn.zhaoyuening.model.Stu;
import cn.zhaoyuening.model.Student;
import cn.zhaoyuening.utils.TransactionExcuter;
import org.hibernate.Query;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * Created by buynow on 16-10-6.
 * HQL Demo
 */
public class Demo_HQL {
    @Test
    public void test(){
        new TransactionExcuter() {
            @Override
            public void transaction() {
                Query query = getSession().createQuery("select s.id,s.name  from Student as s");
                List<Object[]> list = query.list();
                for (Object[] o :
                        list) {
                    System.out.println(Arrays.toString(o));
                }
            }
        }.excute();
    }

    @Test
    //将投影的结果封装到对象中
    public void test2() throws Exception {
        new TransactionExcuter() {
            @Override
            public void transaction() {
                Query query = getSession().createQuery("select new Student (s.id,s.name) from Student as s");
                List<Student> list = query.list();
            }
        }.excute();
    }

    @Test
    //排序
    public void test3() throws Exception {
        new TransactionExcuter() {
            @Override
            public void transaction() {
                //desc id
                Query query = getSession().createQuery("from Student s ORDER BY s.id desc ");
                List<Student> list = query.list();
                for (Student s :
                        list) {
                    System.out.println(s);
                }
            }
        }.excute();
    }

    @Test
    //where
    public void test4() throws Exception {
        new TransactionExcuter() {
            @Override
            public void transaction() {
                Query query = getSession().createQuery("from Student as s where s.id=?");
                //参数占位符
//                Query query = getSession().createQuery("from Student as s where s.id=:id");
//                query.setInteger("id", 22);

                //start index 0
                query.setInteger(0, 22);
                //get unique instance
                Student s = (Student) query.uniqueResult();
                System.out.println(s);
            }
        }.excute();
    }

    @Test
    //分页查询
    public void test5() throws Exception {
        new TransactionExcuter() {
            @Override
            public void transaction() {
                Query query = getSession().createQuery("from Student  as s");
                //start index 0
                query.setFirstResult(0);
                query.setMaxResults(15);
                Classes c = new Classes("99");
                getSession().save(c);
                List<Student> list = query.list();
                for (Student s :
                        list) {
                    s.setClasses(c);
                    System.out.println(s);
                }
            }
        }.excute();
    }

    @Test
    //分组
    public void test6() throws Exception {
        new TransactionExcuter() {
            @Override
            public void transaction() {
                Query query = getSession().createQuery("select count(*) from Student as s where s.classes=:classes group by s.classes ");
                query.setString("classes", "99");
                Object count = query.uniqueResult();
                System.out.println(count);
            }
        }.excute();
    }
}
