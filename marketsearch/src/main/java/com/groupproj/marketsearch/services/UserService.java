package com.groupproj.marketsearch.services;

import java.util.List;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.groupproj.marketsearch.models.DBProduct;
import com.groupproj.marketsearch.models.User;
import com.groupproj.marketsearch.repositories.UserRepo;

@Service
public class UserService {
	@Autowired
	private UserRepo uRepo;
	
	public List<User> getAll(){
		return this.uRepo.findAll();
	}
	public User getById(Long id) {
		return this.uRepo.findById(id).orElse(null);
	}
	public User updateEntry(User toUpdate) {
		return this.uRepo.save(toUpdate);
	}
	public void deleteById(Long id) {
		this.uRepo.deleteById(id);
	}
	public User registerUser(User newEntry) {
		String hash = BCrypt.hashpw(newEntry.getPassword(), BCrypt.gensalt());
		newEntry.setPassword(hash);
		return this.uRepo.save(newEntry);
	}
	public boolean authenticateUser(String email, String password) {
		User user = this.uRepo.findByEmail(email);
		if(user == null) {
			return false;
		}else if(user != null && user.getEmail().equals(email)){
			return BCrypt.checkpw(password, user.getPassword());
		}else {
		return false;
		}
	}
	public User getByEmail(String email) {
		return this.uRepo.findByEmail(email);
	}
	public List<DBProduct> getUserWishlist(User user){
		return user.getUsersWishlist();
	}
}
