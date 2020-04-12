package com.example.restservice;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.restservice.entity.HealthData;
import com.example.restservice.repository.HealthRepository;

@Service
public class HealthService {
	
	@Autowired
	HealthRepository repository;
	
	public HealthData getHealthById(Long id) throws RecordNotFoundException {
		
		Optional<HealthData> healthData = repository.findById(id);
        
        if(healthData.isPresent()) {
            return healthData.get();
        } else {
            throw new RecordNotFoundException("No user health record exist for given id");
        }      
    
	}
	
	 public List<HealthData> getAllHealthData()
	    {
	        List<HealthData> dataList = new ArrayList<HealthData>();
	        		dataList = repository.findAll();
	         
	        if(dataList.size() > 0) {
	            return dataList;
	        } else {
	            return dataList;
	        }
	    }
	
	 public HealthData createOrUpdateHealthData(HealthData entity) throws RecordNotFoundException 
	    {
	        Optional<HealthData> healthData = repository.findById(entity.getUserId());
	         
	       if(healthData.isPresent()) {
	    	   HealthData data = healthData.get();
	    	   data.setBreathProblem(entity.getBreathProblem());
	    	   data.setCold(entity.getCold());
	    	   data.setDryCough(entity.getDryCough());
	    	   data.setPersonName(entity.getPersonName());
	    	   data.setUserId(entity.getUserId());
	    	   data.setHighFever(entity.getHighFever());
	    	   data.setTravelOutsideCountry(entity.getTravelOutsideCountry());
	    	   data.setTravelOutsideState(entity.getTravelOutsideState());
	    	   data.setTravelOutsideDistrict(entity.getTravelOutsideDistrict());
	    	   data = repository.save(data);
	    	   
	    	   return data;
	       }
	       else
	       {
	    	   entity = repository.save(entity);
	    	   return entity;
	       }
	    } 
	 
	 public void deleteEmployeeById(Long id) throws RecordNotFoundException 
	    {
	        Optional<HealthData> data = repository.findById(id);
	         
	        if(data.isPresent()) 
	        {
	            repository.deleteById(id);
	        } else {
	            throw new RecordNotFoundException("No user record exist for given id");
	        }
	    } 

}
