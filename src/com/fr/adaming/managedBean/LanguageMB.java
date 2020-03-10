package com.fr.adaming.managedBean;

import java.io.Serializable;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

public class LanguageMB implements Serializable {

	private static final long serialVersionUID = 1L;

	private Locale locale;

	public Locale getLocale() {
		return locale;
	}

	public void setLocale(Locale locale) {
		this.locale = locale;
	}
	
	public void change(String lang) {
		locale = new Locale(lang);
		System.out.println("DEBUG local : " + this.locale);
		System.out.println("DEBUG var lang : " + lang);
		
		FacesContext.getCurrentInstance()
			.getViewRoot().setLocale(locale);	
		FacesContext.getCurrentInstance()
			.getViewRoot().getLocale();
		
	}

//	
//	private static Map<String,Object> countries;
//	static{
//		countries = new LinkedHashMap<String,Object>();
//		countries.put("English", Locale.ENGLISH); //label, value
//		countries.put("French", Locale.FRENCH);
//	}
//
//	public Map<String, Object> getCountriesInMap() {
//		return countries;
//	}
//
//	
//	public String getLocaleCode() {
//		return localeCode;
//	}
//
//
//	public void setLocaleCode(String localeCode) {
//		this.localeCode = localeCode;
//	}
//
//	//value change event listener
//	public void countryLocaleCodeChanged(ValueChangeEvent e){
//		
//		String newLocaleValue = e.getNewValue().toString();
//		
//		//loop country map to compare the locale code
//                for (Map.Entry<String, Object> entry : countries.entrySet()) {
//        
//        	   if(entry.getValue().toString().equals(newLocaleValue)){
//        		
//        		FacesContext.getCurrentInstance()
//        			.getViewRoot().setLocale((Locale)entry.getValue());
//        		
//        	  }
//               }
//	}

}
