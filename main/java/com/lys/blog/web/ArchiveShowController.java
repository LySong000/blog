package com.lys.blog.web;

import com.lys.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ArchiveShowController {

    @Autowired
    private BlogService blogService;

    @GetMapping("archives")
    public String archives(Model moedl){
        moedl.addAttribute("archiveMap", blogService.archiveBlog());
        moedl.addAttribute("blogCount",blogService.countBlog());
        return "archives";
    }

}
