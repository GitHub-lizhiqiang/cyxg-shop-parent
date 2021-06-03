package com.fh.Vo;

import lombok.Data;
import java.util.List;
@Data
public class PermissionToRole {
    private Long roleId;
    private List<Long> permissionIdList;
}
