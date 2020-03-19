package com.lys.blog.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class VisitorOut {
    private String count;
    private String days;

    public VisitorOut(int c, Long d) {
        this.count = "您是博客的第" + c + "位访客";
        this.days = "已安全运行"+ d + "天";
    }

}
