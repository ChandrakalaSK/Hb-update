package in.ineuron.Test;

import java.io.IOException;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import in.ineuron.Model.Student;
import in.ineuron.Util.HibernateUtil;

public class LoadAndUpdate {

	public static void main(String[] args) throws IOException {
		
		Session session=null;
		Transaction transaction=null;
		boolean flag = false;
		Integer id=2;
		try {
		session=HibernateUtil.geSession();
		Student student=session.get(Student.class, id);
		if(session !=null)
		{
			transaction = session.beginTransaction();
			
			if(transaction != null)
			{
				if(student != null) {
				
				System.out.println(student);
				
				System.out.println();
				
				student.setSaddress("IND");
				student.setSname("sachin");
				session.update(student);
				flag=true;
				}else
				{
					System.out.println("Record not available for updation");
				}
				
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
