package in.ineuron.Test;

import java.io.IOException;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import in.ineuron.Model.Student;
import in.ineuron.Util.HibernateUtil;

public class UpdateApp {

	public static void main(String[] args) throws IOException {
		
		Session session=null;
		Transaction transaction=null;
		boolean flag = false;
		
		try {
		session=HibernateUtil.geSession();
		
		if(session !=null)
		{
			transaction = session.beginTransaction();
			if(transaction != null)
			{
				Student student = new Student();
				student.setSid(99);//Programmer should know that id with record exist.
				student.setSname("Rahul");
				student.setSaddress("MI");
				student.setSage(29);
				session.update(student);
				
				flag=true;
				
			}
			
		}
		}catch (HibernateException e) {
			e.printStackTrace();
			
		}catch (Exception e) {
			e.printStackTrace();
			
		}finally {
			if(flag) {
				transaction.commit();
			System.out.println("Object updated to database");
			System.in.read();
			
			}
			else {
				transaction.rollback();
				System.out.println("Object not updated to database");
			
			}
			System.in.read();
			HibernateUtil.closeSession(session);
			HibernateUtil.closeSessionFactory();
		}

	}

}
