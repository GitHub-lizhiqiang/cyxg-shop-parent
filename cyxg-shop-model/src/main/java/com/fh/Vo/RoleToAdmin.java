package com.fh.Vo;

import lombok.Data;

import java.util.List;
@Data
public class RoleToAdmin {
    private Long  userId;
    private List<Long> roleIdList;
}
