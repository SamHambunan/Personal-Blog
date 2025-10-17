package com.sam.personalBlog.Blog.repository;

import com.sam.personalBlog.model.Blog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface BlogRepository extends JpaRepository<Blog, Integer> {
    public List<Blog> findByTitleContainingIgnoreCaseOrderByIdDesc(String title);


}
