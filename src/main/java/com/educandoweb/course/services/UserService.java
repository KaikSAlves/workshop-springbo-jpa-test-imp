package com.educandoweb.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.educandoweb.course.entities.User;
import com.educandoweb.course.repositories.UserRepository;
import com.educandoweb.course.services.exceptions.ResourceNotFoundException;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public List<User> findAll(){
		return this.userRepository.findAll();
	}
	
	public User findById(Long id) {
		Optional<User> obj = this.userRepository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	public User insert(User obj) {
		return userRepository.save(obj);
	}
	
	public void delete(Long id) {
		userRepository.deleteById(id);
	}
	
	public User update(Long id, User obj) {
		User ent = userRepository.getReferenceById(id);
		updateData(ent, obj);
		return userRepository.save(ent);
	}

	private void updateData(User ent, User obj) {
		ent.setName(obj.getName());
		ent.setEmail(obj.getEmail());
		ent.setPhone(obj.getPhone());
	}
}

