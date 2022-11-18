package com.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.entities.Category;

interface  CategoryRepo extends  JpaRepository<Category, Integer>{
	
}
