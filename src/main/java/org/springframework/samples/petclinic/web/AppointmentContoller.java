package org.springframework.samples.petclinic.web;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Owner;
import org.springframework.samples.petclinic.model.Vet;
import org.springframework.samples.petclinic.service.AppointmentService;
import org.springframework.samples.petclinic.service.ClinicService;
import org.springframework.samples.petclinic.service.DailyAppointmentSlots;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller

public class AppointmentContoller {

	
	 private final ClinicService clinicService;
	 private final AppointmentService appointmentService;

	    @Autowired
	    public AppointmentContoller(ClinicService clinicService,AppointmentService appointmentService) {
	        this.clinicService = clinicService;
	        this.appointmentService = appointmentService;
	    }
	    @RequestMapping(value = "/appointment", method = RequestMethod.GET)
	    public String getappointmentForm(  Map<String, Object> model){
	    	
	         
	         Collection<Owner> owners = this.clinicService.findOwnerByLastName("");
	     
	        Collection<Vet>  vets = this.clinicService.findVets();
	          
	             model.put("vets",vets);
	             model.put("owners", owners);
	             return "/appointements/aptList";     
	         
	
	    }
	    @RequestMapping(value = "/vet/{id}/appointment.json", method = RequestMethod.GET)
	    public 
	    @ResponseBody
	    List<DailyAppointmentSlots> getAppointmentByVet(Vet vet){
	    	Set<DailyAppointmentSlots>  dailyappointmentslots = new HashSet<DailyAppointmentSlots>();
	    	Calendar start = Calendar.getInstance();
    		start.setTime(new Date());
	    	for(int i = 1;i<6;i++){
	    		
	    		start.add(Calendar.DATE, 0);
	    		Date today = start.getTime();
	    		DailyAppointmentSlots dailyslots =this.appointmentService.getAvailaibleAppointmentForVet(vet, today);
	    		start.add(Calendar.DATE, 1);
	    		
	    		dailyappointmentslots.add(dailyslots);
	    		
	    	}
	    	
	    	return (List<DailyAppointmentSlots>) dailyappointmentslots;
	    }

	    
}
