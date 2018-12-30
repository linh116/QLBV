package com.qlbv.dao;

import com.qlbv.model.MedicalRecord;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.util.List;

public class MedicalRecordDao extends HibernateDaoSupport {

	public void save(MedicalRecord medicalRecord){
		getHibernateTemplate().save(medicalRecord);
	}
	
	public void update(MedicalRecord medicalRecord){
		getHibernateTemplate().update(medicalRecord);
	}
	
	public void delete(MedicalRecord medicalRecord){
		getHibernateTemplate().delete(medicalRecord);
	}

    public List<MedicalRecord> getListMedicalRecord(List<Long> listId) {
		Session session = getHibernateTemplate().getSessionFactory().openSession();
		Query query2 = session.createQuery("from MedicalRecord where medicalRecordId IN (:ids)").setParameterList("ids", listId);
		query2.list();
		List list = query2.list();
		if (CollectionUtils.isEmpty(list)){
			return null;
		}
		session.close();
		return list;
    }
}
