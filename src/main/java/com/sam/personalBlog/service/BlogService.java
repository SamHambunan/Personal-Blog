package com.sam.personalBlog.service;


import com.sam.personalBlog.Blog.repository.BlogRepository;
import com.sam.personalBlog.exception.ResourceNotFoundException;
import com.sam.personalBlog.model.Blog;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class BlogService {
    private final BlogRepository blogRepository;
    public BlogService(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    public Blog create(Blog blog) {
        return blogRepository.save(blog);
    }

    public Blog queryBlog(int id)
    {
        return blogRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Can't find resource with ID: "+ id));

    }
    public List<Blog> queryBlog(String title)
    {
        List<Blog> blogs;
        if (title == null) {
            blogs = blogRepository.findAll();
            if(blogs.isEmpty())
            {
                throw new ResourceNotFoundException("There are no blogs posted yet");
            }
            return blogRepository.findAll();
        }

        blogs = blogRepository.findByTitleContainingIgnoreCaseOrderByIdDesc(title);

        if (blogs.isEmpty()) {
            throw new ResourceNotFoundException("Can't find resource containing title: " + title);
        }

        return blogs;
    }

    public void deleteBlog(int id)
    {
        Blog gottenBlog = blogRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Can't find resource containing id: " + id));
        blogRepository.delete(gottenBlog);
    }

    public Blog putinBlog(int id, Blog blog)
    {
      Blog gottenBlog = blogRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Can't find resource containing id: " + id));
      gottenBlog.setContent(blog.getContent());
      gottenBlog.setTitle(blog.getTitle());
      blogRepository.save(gottenBlog);
      return gottenBlog;
    }
}
