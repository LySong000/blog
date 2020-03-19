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
@Table(name = "t_comment")
public class Comment {
    @Id
    @GeneratedValue
    private Long id; //id
    private String nickname; //昵称
    private String email; //邮箱
    private String content; //评论内容
    private String avatar; //头像
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime; //创建时间
    private boolean adminComment;
    @ManyToOne()
    private Blog blog;



    @OneToMany(mappedBy = "parentComment")
    private List<Comment> replyComments = new ArrayList<>();

    @ManyToOne
    private Comment parentComment;
}
