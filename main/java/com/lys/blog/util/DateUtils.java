package com.lys.blog.util;

import com.lys.blog.entity.Visitor;
import com.lys.blog.service.VisitorService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

public class DateUtils {
    public static long daysBetween(Date one, Date two) {
        long difference =  (one.getTime()-two.getTime())/86400000;
        return Math.abs(difference);
    }

}
