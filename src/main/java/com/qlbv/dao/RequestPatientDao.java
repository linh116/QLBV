package com.qlbv.dao;

import com.qlbv.model.RequestPatient;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.util.Calendar;
import java.util.Comparator;
import java.util.List;

public class RequestPatientDao extends HibernateDaoSupport {
	
	public void save(RequestPatient requestPatient){
		getHibernateTemplate().save(requestPatient);
	}
	
	public void update(RequestPatient requestPatient){
		getHibernateTemplate().update(requestPatient);
	}
	
	public void delete(RequestPatient requestPatient){
		getHibernateTemplate().delete(requestPatient);
	}


	public List<RequestPatient> findAllRequestToday() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		long from = cal.getTimeInMillis();

		cal.add(Calendar.DATE, 1);
		long to = cal.getTimeInMillis();


		List list = getHibernateTemplate().find("from RequestPatient where createDtm>=? and createDtm<? and status <> 'DONE'"
				, new Object[]{from, to});
		if (CollectionUtils.isEmpty(list)){
			return null;
		}
		list.sort(Comparator.comparing(RequestPatient::getCreateDtm));
		return list;
	}

    public RequestPatient findRequestById(Long requestId) {
		List list = getHibernateTemplate().find("from RequestPatient where requestId = ?	"
				, new Object[]{requestId});
		if (CollectionUtils.isEmpty(list)){
			return null;
		}
		return (RequestPatient) list.get(0);
    }
}
