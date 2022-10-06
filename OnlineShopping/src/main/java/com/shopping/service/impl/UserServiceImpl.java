package com.shopping.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.shopping.dao.UserDAO;
import com.shopping.model.Role;
import com.shopping.model.User;
import com.shopping.model.UserPrincipal;
import com.shopping.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService, UserDetailsService {

	@Autowired
	private UserDAO userDao;

	@Override
	public void insert(User userDTO) {
		Role role = new Role();
		role.setRoleId(userDTO.getRole().getRoleId());

		User user = new User();
		user.setUserId(userDTO.getUserId());
		user.setEmail(userDTO.getEmail());
		user.setPhone(userDTO.getPhone());
		user.setAddress(userDTO.getAddress());
		user.setAvatar(userDTO.getAvatar());
		user.setFullname(userDTO.getFullname());
		user.setVerify(userDTO.isVerify());
		user.setGender(userDTO.isGender());
		user.setPassword(userDTO.getPassword());
		user.setRole(role);

		userDao.insert(user);
	}

	@Override
	public void update(User userDTO) {
		Role role = new Role();
		role.setRoleId(userDTO.getRole().getRoleId());

		User user = new User();
		user.setUserId(userDTO.getUserId());
		user.setEmail(userDTO.getEmail());
		user.setPhone(userDTO.getPhone());
		user.setAddress(userDTO.getAddress());
		user.setAvatar(userDTO.getAvatar());
		user.setFullname(userDTO.getFullname());
		user.setVerify(userDTO.isVerify());
		user.setGender(userDTO.isGender());
		user.setPassword(userDTO.getPassword());
		user.setRole(role);

		userDao.update(user);
	}

	@Override
	public void delete(long userId) {
		// TODO Auto-generated method stub
		userDao.delete(userDao.findById(userId));
	}

	@Override
	public User findById(long userId) {
		User user = userDao.findById(userId);
		Role roleDTO = new Role();
		roleDTO.setRoleId(user.getRole().getRoleId());
		roleDTO.setRoleName(user.getRole().getRoleName());

		User userDTO = new User();
		userDTO.setUserId(user.getUserId());
		userDTO.setEmail(user.getEmail());
		userDTO.setPhone(user.getPhone());
		userDTO.setAddress(user.getAddress());
		userDTO.setAvatar(user.getAvatar());
		userDTO.setFullname(user.getFullname());
		userDTO.setVerify(user.isVerify());
		userDTO.setGender(user.isGender());
		userDTO.setPassword(user.getPassword());
		userDTO.setRole(roleDTO);
		return userDTO;
	}

	@Override
	public List<User> findAll(int pageIndex, int PageSize) {
		List<User> users = userDao.findAll(pageIndex, PageSize);
		List<User> userDTOs = new ArrayList<>();
		for (User user : users) {
			Role roleDTO = new Role();
			roleDTO.setRoleId(user.getRole().getRoleId());
			roleDTO.setRoleName(user.getRole().getRoleName());

			User userDTO = new User();
			userDTO.setUserId(user.getUserId());
			userDTO.setEmail(user.getEmail());
			userDTO.setPhone(user.getPhone());
			userDTO.setAddress(user.getAddress());
			userDTO.setAvatar(user.getAvatar());
			userDTO.setFullname(user.getFullname());
			userDTO.setVerify(user.isVerify());
			userDTO.setGender(user.isGender());
			userDTO.setPassword(user.getPassword());
			userDTO.setRole(roleDTO);

			userDTOs.add(userDTO);
		}
		return userDTOs;
	}

	@Override
	public User findByEmailOrPhoneAndPassword(String account, String password, boolean verity) {
		User user = userDao.findByEmailOrPhoneAndPassword(account, password, verity);
		Role roleDTO = new Role();
		roleDTO.setRoleId(user.getRole().getRoleId());
		roleDTO.setRoleName(user.getRole().getRoleName());

		User userDTO = new User();
		userDTO.setUserId(user.getUserId());
		userDTO.setEmail(user.getEmail());
		userDTO.setPhone(user.getPhone());
		userDTO.setAddress(user.getAddress());
		userDTO.setAvatar(user.getAvatar());
		userDTO.setFullname(user.getFullname());
		userDTO.setVerify(user.isVerify());
		userDTO.setGender(user.isGender());
		userDTO.setPassword(user.getPassword());
		userDTO.setRole(roleDTO);
		return userDTO;
	}

	@Override
	public UserDetails loadUserByUsername(String account) throws UsernameNotFoundException {
		User user = userDao.loadUserByUsername(account);
		if (user == null) {
			throw new UsernameNotFoundException("Not Found!");
		}

		List<SimpleGrantedAuthority> roleList = new ArrayList<>();

		roleList.add(new SimpleGrantedAuthority(user.getRole().getRoleName()));

		UserPrincipal userPrincipal = new UserPrincipal(user.getEmail(), user.getPhone(), user.getPassword(), roleList);
		userPrincipal.setUserId(user.getUserId());
		userPrincipal.setEmail(user.getEmail());
		userPrincipal.setPhone(user.getPhone());
		userPrincipal.setAddress(user.getAddress());
		userPrincipal.setAvatar(user.getAvatar());
		userPrincipal.setFullname(user.getFullname());
		userPrincipal.setVerify(user.isVerify());
		userPrincipal.setGender(user.isGender());
		userPrincipal.setPassword(user.getPassword());
		userPrincipal.setRole(user.getRole());
		return userPrincipal;
	}

	@Override
	public int count() {
		return userDao.count();
	}

	@Override
	public User findByEmail(String email) {
		User user = userDao.findByEmail(email);
		if (user != null) {
			User userDTO = new User();
			Role roleDTO = new Role();
			roleDTO.setRoleId(user.getRole().getRoleId());
			roleDTO.setRoleName(user.getRole().getRoleName());

			userDTO.setUserId(user.getUserId());
			userDTO.setEmail(user.getEmail());
			userDTO.setPhone(user.getPhone());
			userDTO.setAddress(user.getAddress());
			userDTO.setAvatar(user.getAvatar());
			userDTO.setFullname(user.getFullname());
			userDTO.setVerify(user.isVerify());
			userDTO.setGender(user.isGender());
			userDTO.setPassword(user.getPassword());
			userDTO.setRole(roleDTO);
			return userDTO;
		}
		return null;
	}

}
