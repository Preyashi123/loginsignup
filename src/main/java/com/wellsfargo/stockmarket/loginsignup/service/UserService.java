package com.wellsfargo.stockmarket.loginsignup.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;


import com.wellsfargo.stockmarket.loginsignup.entity.User;
import com.wellsfargo.stockmarket.loginsignup.repository.UserRepository;


@Service
public class UserService {
	@Autowired
	UserRepository userRepository;
	User user;
	String res;

	public String findbyID(String username, String password) {

		user = userRepository.findByUserName(username);
		if (user != null) {

			if (user.getPassword().equals(password)) {

				res = "SUCCESS";
				return res;
			} else {

				res = "wrong password";
				return res;

			}

		} else {

			res = "USER NOT PRESENT";
			return res;

		}

	}

	public User addUser(String username, String password, String email) {
		

		user = userRepository.findByUserName(username);
		
		if (user == null) {
			
				User user = new User(username, password, email, "user");
				user.setVerified("Y");

				res = "USER ADDED";
				return userRepository.save(user);
			
		} else {

			res = "USER ALREADY PRESENT";
			return null;
		}

	}

	public User updateUser(String username, String password, String email) {

		user = userRepository.findByUserName(username);

		if (user != null && user.getUserName().equals(username)) {
			if(email!=null)
				user.setEmail(email);
			if(password!=null)
			user.setPassword(password);

			return userRepository.save(user);

		} else {

			res = " USER NOT PRESENT";
			return null;
		}

	}

}
