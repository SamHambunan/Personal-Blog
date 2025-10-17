package com.sam.personalBlog.controller;

import com.sam.personalBlog.model.Blog;
import com.sam.personalBlog.service.BlogService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/blogs")
public class BlogController {
    private final BlogService blogService;
    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    @PostMapping("/create")
    public ResponseEntity<Blog> create(@Valid @RequestBody Blog blog) {
        Blog savedBlog = blogService.create(blog);
     return ResponseEntity.status(HttpStatus.CREATED).body(savedBlog);
    }

   @GetMapping(path = "{idNo}")
    public ResponseEntity<Blog> queryBlog(@PathVariable("idNo") int id){
        Blog savedBlog = blogService.queryBlog(id);
        return ResponseEntity.ok(savedBlog);
   }

    @GetMapping("")
    public ResponseEntity<List<Blog>> queryBlog(@RequestParam(value = "title",required = false) String title) {
        List<Blog> savedBlogs = blogService.queryBlog(title);
        return ResponseEntity.ok(savedBlogs);
    }

    @DeleteMapping(path = "{idNo}")
    public ResponseEntity<Void> deleteBlog(@PathVariable("idNo") int id)
    {
        blogService.deleteBlog(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping(path = "{idNo}")
    public ResponseEntity<Blog> putInMap(@PathVariable("idNo") int id, @RequestBody Blog blog)
    {
        Blog updatedBlog = blogService.putinBlog(id,blog);
        return ResponseEntity.ok(updatedBlog);
    }




}
