package com.lys.blog.dao;

import com.lys.blog.entity.Type;
import com.lys.blog.entity.User;
import com.lys.blog.entity.Visitor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VisitorRepository extends JpaRepository<Visitor,Long> {

}
