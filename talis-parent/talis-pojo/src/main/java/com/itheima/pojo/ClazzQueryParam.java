package com.itheima.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClazzQueryParam {
    private String name;
    private LocalDate beginDate;
    private LocalDate endDate;
    private Integer page =1;
    private Integer pageSize=10;
}
