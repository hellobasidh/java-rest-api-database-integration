package com.example.restservice;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.restservice.entity.PersonData;
import com.example.restservice.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository repository;

	public PersonData getUserById(Long id) throws RecordNotFoundException {

		Optional<PersonData> healthData = repository.findById(id);

		if (healthData.isPresent()) {
			return healthData.get();
		} else {
			throw new RecordNotFoundException("No user health record exist for given id");
		}

	}

	public List<PersonData> getAllUsers() {
		List<PersonData> dataList = new ArrayList<PersonData>();
		dataList = repository.findAll();

		if (dataList.size() > 0) {
			return dataList;
		} else {
			return dataList;
		}
	}

	public PersonData createOrUpdateUser(PersonData entity) throws RecordNotFoundException {
		Optional<PersonData> personData = repository.findById(entity.getUserId());

		if (personData.isPresent()) {

			PersonData data = personData.get();
			data.setUserId(entity.getUserId());
			data.setPersonName(entity.getPersonName());
			data.setAge(entity.getAge());
			data.setDocumentImage(entity.getDocumentImage());
			data.setDocumentNumber(entity.getDocumentNumber());
			data.setSelfImage(entity.getSelfImage());
			data.setEmailAddress(entity.getEmailAddress());

			data = repository.save(data);

			return data;
		} else {
			entity = repository.save(entity);
			return entity;
		}
	}

	public void deleteUserById(Long id) throws RecordNotFoundException {
		Optional<PersonData> data = repository.findById(id);

		if (data.isPresent()) {
			repository.deleteById(id);
		} else {
			throw new RecordNotFoundException("No user record exist for given id");
		}
	}
}
