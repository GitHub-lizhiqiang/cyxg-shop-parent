package com.fh.usertomenu.entity;

import lombok.Data;

import java.util.List;

@Data
public class MenuToUser {
    private Long userId;
    private List<Long> menuIdList;
}
