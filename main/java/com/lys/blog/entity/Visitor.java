package com.lys.blog.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "t_visitor")
public class Visitor {
    @Id
    @GeneratedValue
    private Long id; //id
    private int count; //访问数量
    @Temporal(TemporalType.TIMESTAMP)
    private Date begintime; //开机时间
}
