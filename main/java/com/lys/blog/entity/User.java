package com.lys.blog.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@ToString
@Entity
@Table(name = "t_user")
public class User {
    @Id
    @GeneratedValue
    private Long id; //id
    private String nickname; //昵称
    private String username; //用户名
    private String password; //密码
    private String email; //邮箱
    private String avatar; //头像
    private Integer type; //用户类型
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime; //创建时间
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime; //更新时间

    @OneToMany(mappedBy = "user")
    private List<Blog> blogs = new ArrayList<>();

}
