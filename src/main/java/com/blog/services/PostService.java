package com.blog.services;

import java.util.List;

import com.blog.entities.Post;
import com.blog.payloads.PostDto;

public interface PostService {
	Post createPost(PostDto postDto, Integer userId, Integer category);
	
	Post updatePost(PostDto postDto, Integer postId);
	
	void deletePost(Integer postId);
	
	List<Post> getAllPost();
	
	Post getPostById(Integer postId);
	
	List<Post>getPostByCategory(Integer categoryId);
	
	List<Post>getPostByUser(Integer userId);
	
	

}
