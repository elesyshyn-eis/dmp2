package com.ebsco.eis.medical;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
 
@Component
public class ApplicationContextProvider implements ApplicationContextAware {
 
    private static ApplicationContext CONTEXT;
 
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        CONTEXT = applicationContext;
    }
 
    /**
     * Get a Spring bean by type.
     **/
    public static <T> T getBean(String beanName, Class<T> beanClass) {
    	
    	try {
    		return beanClass.cast(getBean(beanName));
    	} catch (Exception ex) {
    		return null;
    	}
    }
 
    /**
     * Get a Spring bean by name.
     **/
    public static Object getBean(String beanName) {
    	try {
    		return CONTEXT.getBean(beanName);
    	} catch (Exception ex) {
    		return null;
    	} 
    }
}