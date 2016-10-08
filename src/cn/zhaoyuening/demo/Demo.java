package cn.zhaoyuening.demo;

import static org.junit.Assert.*;

import java.util.List;
import java.util.UUID;

import cn.zhaoyuening.model.*;
import org.hibernate.Query;
import org.junit.Test;

import cn.zhaoyuening.utils.TransactionExcuter;

public class Demo {
	@Test
	public void test() throws Exception {
		new TransactionExcuter() {
			
			@Override
			public void transaction() {
				Persion p = new Persion();
				p.setName("小房子");
				p.setAge(18);
				p.setGender('m');
				getSession().save(p);
				System.out.println(p.getId());
			}
		}.excute();
	}
	@Test
	//测试一层缓存
	public void testCache_one() throws Exception {
		new TransactionExcuter() {
			
			@Override
			public void transaction() {
				//第一次查询：从数据库中获取数据 使用查询语句 并且将持久化对象保存到一级缓存中
				Persion p1 = (Persion) getSession().get(Persion.class, 1);
				int i=0;
				//第二次查询：直接从一级缓存从读取对象 不需要使用查询语句
				Persion p2 = (Persion) getSession().get(Persion.class, 1);
				//两个对象是同一个对象
				System.out.println(p1==p2);
			}
		}.excute();;
	}
	
	@Test
	//测试session中的缓存与快照
	public void test2() throws Exception {
		new TransactionExcuter() {
			
			@Override
			public void transaction() {
				/**
				 * 在hibernate中当数据导入到session缓存中会保存两份（一式两份）
				 * 一份快照 一份缓存将缓存返回给用户
				 * 如果使用update等语句或者提交时缓存对象与快照对象不一致
         		 * 将会更新快照 并使用update语句等写入新数据到数据库
				 */
				Persion p = (Persion) getSession().get(Persion.class, 1);
				p.setName("赵岳宁5");
				//与快照中的对象对比
				//相同不操作
				getSession().update(p);
				System.out.println("stop");
			}
		}.excute();;
	}
	
	@Test
	public void test3() throws Exception {
		new TransactionExcuter() {
			
			@Override
			public void transaction() {
				List<Persion> list = getSession().createQuery("from "+Persion.class.getSimpleName()).list();
				System.out.println(1);
				List<Persion> list2 = getSession().createQuery("from "+Persion.class.getSimpleName()).list();
				System.out.println(2);
				getSession().get(Persion.class, 1);
				System.out.println(3);
				System.out.println(list);
			}
		}.excute();
	}

	@Test
	public void test_one2many(){
		new TransactionExcuter(){
			@Override
			public void transaction() {
				Classes c = new Classes("k100");
				Student s1 = new Student("zhao");
				Student s2 = new Student("yue");
				Student s3 = new Student("ning");
				c.addStudent(s1);
				c.addStudent(s2);
				c.addStudent(s3);
				getSession().save(c);
				getSession().save(s1);
				getSession().save(s2);
				getSession().save(s3);
			}
		}.excute();
	}

	@Test
	public void test_one2many_remove() throws Exception {
		new TransactionExcuter() {
			@Override
			public void transaction() {
				Classes c = (Classes) getSession().get(Classes.class, "99");
				getSession().delete(c);
				System.out.println(c);
			}
		}.excute();
	}

	@Test
	public void test_one2many_query() throws Exception {
		new TransactionExcuter() {
			@Override
			public void transaction() {
				Classes c = (Classes) getSession().get(Classes.class, "k100");
				for (Student student :
						c.getStudents() ) {
					System.out.println(student);
				}
			}
		}.excute();
	}

	@Test
	public void test4() throws Exception {
		new TransactionExcuter() {
			@Override
			public void transaction() {
				Classes c = new Classes("100");
				Student s1= new Student("STU3");
				Student s2= new Student("STU4");
				s1.setClasses(c);
				s2.setClasses(c);
				getSession().save(c);
				getSession().save(s1);
				getSession().save(s2);
			}
		}.excute();

	}

	@Test
	public void test_update() throws Exception {
		new TransactionExcuter() {
			@Override
			public void transaction() {
				Classes c = (Classes) getSession().get(Classes.class,"100");
				Student s1 = (Student) getSession().get(Student.class, 15);
				Student s2 = (Student) getSession().get(Student.class, 16);
				Student s3 = (Student) getSession().get(Student.class, 17);
				s1.setClasses(c);
				s2.setClasses(c);
				s3.setClasses(c);
			}
		}.excute();
	}

	@Test
	//cascade="save-update"
	public void test_cascade() throws Exception {
		new TransactionExcuter() {
			@Override
			public void transaction() {
				Classes c = new Classes("100");
				Student s1= new Student("STU3");
				Student s2= new Student("STU4");
				c.addStudent(s1);
				c.addStudent(s2);
				getSession().save(c);
			}
		}.excute();
	}

	/**
	 * Many to many
	 */

	@Test
	public void test_many2many() throws Exception {
		new TransactionExcuter() {
			@Override
			public void transaction() {
				Stu sz = new Stu("赵岳宁");
				Stu sy = new Stu("杨凡");
				Course cm = new Course("math");
				Course ce = new Course("english");

				cm.addStu(sz);
				cm.addStu(sy);
				ce.addStu(sy);

				getSession().save(cm);
				getSession().save(ce);
			}
		}.excute();

	}

	@Test
	public void test_addstudent() throws Exception {
		new TransactionExcuter() {
			@Override
			public void transaction() {
				for (int i = 0; i < 30; i++) {
					Student s = new Student(UUID.randomUUID().toString());
					getSession().save(s);
				}

			}
		}.excute();

	}

	@Test
	public void test_updatestudent() throws Exception {
		new TransactionExcuter() {
			@Override
			public void transaction() {
				Classes c = (Classes) getSession().get(Classes.class, "100");
				Query query = getSession().createQuery("from Student ");
				List<Student> list = query.list();
				for (Student s:
					 list) {
					s.setClasses(c);
				}
			}
		}.excute();

	}
}
