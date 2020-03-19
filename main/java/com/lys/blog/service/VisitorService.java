package com.lys.blog.service;

import com.lys.blog.entity.Visitor;
import com.lys.blog.vo.VisitorOut;

public interface VisitorService {
    VisitorOut getData();
    Visitor updateData(Visitor visitor);
}
