package com.qlbv.dao;

import com.qlbv.model.Patient;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.util.List;

public class PatientDao extends HibernateDaoSupport {

	public void save(Patient patient){
		getHibernateTemplate().save(patient);
	}
	
	public void update(Patient patient){
		getHibernateTemplate().update(patient);
	}
	
	public void delete(Patient patient){
		getHibernateTemplate().delete(patient);
	}

    public List<Patient> getListPatient(List<Long> listId) {
		Session session = getHibernateTemplate().getSessionFactory().openSession();
		Query query2 = session.createQuery("from Patient where patientId IN (:ids)").setParameterList("ids", listId);
		query2.list();
		List list = query2.list();
		if (CollectionUtils.isEmpty(list)){
			return null;
		}
		session.close();
		return list;
    }

    public List<Patient> getListPatient(String name, Long birthday) {
		try {
			Session session = getHibernateTemplate().getSessionFactory().openSession();
			String queryStr = buildQuerySelectWithNameAndBirthday(name, birthday);
			Query query2 = session.createQuery(queryStr);

			if(StringUtils.isNotEmpty(name)){
				query2.setParameter("patientName", "%" + name + "%");
				query2.setParameter("phone", "%" + name + "%");
			}
			if(birthday != null && birthday > 0){
				query2.setParameter("birthday", birthday);
			}

			query2.list();
			List list = query2.list();
			if (CollectionUtils.isEmpty(list)){
				return null;
			}
			session.close();
			return list;
		}catch (Exception e){
			e.printStackTrace();
			return null;
		}
    }

	private String buildQuerySelectWithNameAndBirthday(String name, Long birthday) {
		String rs = "FROM Patient WHERE 1 = 1 ";
		if(StringUtils.isNotEmpty(name)){
			rs += "AND (patientName LIKE :patientName OR phone LIKE :phone)";
		}
		if(birthday != null && birthday > 0){
			rs += "AND birthday = :birthday ";
		}
		return rs;
	}
}
