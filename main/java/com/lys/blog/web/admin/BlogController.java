package com.lys.blog.web.admin;

import com.lys.blog.entity.Blog;
import com.lys.blog.entity.User;
import com.lys.blog.service.BlogService;
import com.lys.blog.service.TagService;
import com.lys.blog.service.TypeService;
import com.lys.blog.vo.BlogQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("admin")
public class BlogController {
    private static final String INPUT = "admin/blogs-input";
    private static final String LIST = "admin/blogs";
    private static final String REDIRECT_LIST = "redirect:/admin/blogs";
    @Autowired
    private BlogService blogService;
    @Autowired
    private TypeService typeService;
    @Autowired
    private TagService tagService;

    /**
     * 博客列表
     * 分页查询，一页为十条数据
     * 采取DESC排序（倒序）
     * @param pageable
     * @param model
     * @param blog
     * @return
     */
    @GetMapping("/blogs")
    public String list(@PageableDefault(size = 10, sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable, Model model, BlogQuery blog){
        model.addAttribute("types", typeService.listType());
        model.addAttribute("page", blogService.listBlog(pageable,blog));
        return LIST;
    }

    /**
     * 博客查询
     * 使用了AJAX技术，只更新一部分页面
     * @param pageable
     * @param model
     * @param blog
     * @return
     */
    @PostMapping("/blogs/search")
    public String search(@PageableDefault(size = 10, sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable, Model model, BlogQuery blog){
        model.addAttribute("page", blogService.listBlog(pageable,blog));
        return "admin/blogs :: blogList";
    }

    /**
     * 博客编辑
     * 从前端页面得到一个id
     * 通过id得到这个博客并将对象传到前端
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/blogs/{id}/input")
    public String editinput(@PathVariable Long id, Model model){
        setTypeAndTag(model);
        Blog b = blogService.getBlog(id);
        b.init();
        model.addAttribute("blog", b);
        return INPUT;
    }

    /**
     * 查询所有博客标签和分类
     * @param model
     */
    private void setTypeAndTag(Model model){
        model.addAttribute("types", typeService.listType());
        model.addAttribute("tags", tagService.listTag());
    }

    /**
     * 博客新增
     * new一个空对象传到前端，并将所有标签，分类信息传到前端进行显示
     * 空对象是因为新增与编辑共用页面，为了防止报错传过去一个空对象
     * @param model
     * @return
     */
    @GetMapping("/blogs/input")
    public String input(Model model){
        setTypeAndTag(model);
        model.addAttribute("blog", new Blog());
        return INPUT;
    }

    /**
     * 处理post表单
     * 首先得到session中的用户信息，设置为作者
     * 校验传过来的标签分类是否和数据库一直，设置标签分类
     * 若前端传过来的id为空，则说明是新增，使用saveBlog
     * 若id不为空，则说明是更新，使用updateBlog
     * @param blog
     * @param session
     * @param attributes
     * @return
     */
    @PostMapping("/blogs")
    public String post(Blog blog, HttpSession session, RedirectAttributes attributes){
        String flag = blog.getFlag();
        System.out.println(flag);
        blog.setUser((User)session.getAttribute("user"));
        blog.setType(typeService.getType(blog.getType().getId()));
        blog.setTags(tagService.listTag(blog.getTagIds()));
        Blog b;
        if(blog.getId() == null){
            b = blogService.saveBlog(blog);
        }else {
            b = blogService.updateBlog(blog.getId(), blog);
        }

        if(b == null){
            attributes.addFlashAttribute("message","操作失败");
        }else {
            attributes.addFlashAttribute("message","操作成功");
        }
        return  REDIRECT_LIST;
    }

    /**
     * 根据前端传过来的id进行删除操作
     * @param id
     * @param attributes
     * @return
     */
    @GetMapping("/blogs/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes attributes){
        blogService.deleteBlog(id);
        attributes.addFlashAttribute("message","删除成功");
        return  REDIRECT_LIST;
    }


}
