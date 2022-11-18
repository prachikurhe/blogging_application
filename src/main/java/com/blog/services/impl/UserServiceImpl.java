package com.blog.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.entities.User;
import com.blog.exceptions.ResourceNotFoundException;
import com.blog.payloads.UserDto;
import com.blog.repositories.UserRepo;
import com.blog.services.UserService;
@Service
public class UserServiceImpl implements UserService {
	
    @Autowired
	private UserRepo uerRepo;
    
    @Autowired
    private ModelMapper modelMapper;
    
	@Override
	public UserDto createUser(UserDto userDto) {
		
		User user =this.dtoToUser(userDto);   
		User savedUser=this.uerRepo.save(user);
		return this.userToDto(savedUser);
	}
////////////
	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {
		User user =this.uerRepo.findById(userId).
				orElseThrow(()->new ResourceNotFoundException("User","Id",userId));
		
		user.setId(userDto.getId());
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setAbout(userDto.getAbout());
		
		User usdateUser=this.uerRepo.save(user);
		UserDto userDto1=this.userToDto(usdateUser);
		return userDto1;
	}
//////////////
	@Override
	public UserDto getUserById(Integer userId) {
		User user =this.uerRepo.findById(userId).
				orElseThrow(()->new ResourceNotFoundException("User","Id",userId));
		
		return this.userToDto(user);
	}
//////////////
	@Override
	public List<UserDto> getAllUsers() {
		 List<User>users=this.uerRepo.findAll();
		 List<UserDto>userDtos=users.stream().map(user->this.userToDto(user)).collect(Collectors.toList());
		return userDtos;
	}
/////////////
	@Override
	public void deleteUser(Integer userId) {
		User user=this.uerRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User", "Id", userId));

	}
/////////////	
	public User dtoToUser(UserDto userDto) {
		User user=this.modelMapper.map(userDto, User.class);
//		User user=new User();
//		user.setId(userDto.getId());
//		user.setName(userDto.getName());
//		user.setEmail(userDto.getEmail());
//		user.setPassword(userDto.getPassword());
//		user.setAbout(userDto.getAbout());
		return user;
	}
////////////	
	public UserDto userToDto(User user) {
		UserDto userDto=this.modelMapper.map(user, UserDto.class);
//		UserDto userDto=new UserDto();
//		userDto.setId(user.getId());
//		userDto.setName(user.getName());
//		userDto.setEmail(user.getEmail());
//		userDto.setPassword(user.getPassword());
//		userDto.setAbout(user.getAbout());
		return userDto;
	}

}
