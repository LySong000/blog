package com.lys.blog.service;

import com.lys.blog.dao.VisitorRepository;
import com.lys.blog.entity.Visitor;
import com.lys.blog.util.DateUtils;
import com.lys.blog.vo.VisitorOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class VisitorServiceImpl implements VisitorService {
    @Autowired
    private VisitorRepository visitorRepository;
    @Override
    public VisitorOut getData() {
        Long id = 1L;
        Visitor visitor = visitorRepository.findOne(id);
        visitor.setCount(visitor.getCount()+1);
        updateData(visitor);
        VisitorOut visitorOut =  new VisitorOut(visitor.getCount(), DateUtils.daysBetween(new Date(),visitor.getBegintime()));
        return visitorOut;
    }
    @Transactional
    @Override
    public Visitor updateData(Visitor visitor) {
        return visitorRepository.save(visitor);
    }
}
