package com.qlbv.bo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BoManager {
    public static final ApplicationContext appContext =
            new ClassPathXmlApplicationContext("spring/config/BeanLocations.xml");

    public static ApplicationContext getAppContext(){
        return appContext;
    }
    public static UserBo userBo = (UserBo) BoManager.getAppContext().getBean("userBo");
    public static PatientBo patientBo = (PatientBo) BoManager.getAppContext().getBean("patientBo");
    public static RequestPatientBo requestPatientBo = (RequestPatientBo) BoManager.getAppContext().getBean("requestPatientBo");
    public static MedicalRecordBo medicalRecordBo = (MedicalRecordBo) BoManager.getAppContext().getBean("medicalRecordBo");

}
