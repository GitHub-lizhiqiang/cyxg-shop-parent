package com.fh.usertomenu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.fh.permission.entity.UmsPermission;
import com.fh.permission.mapper.UmsPermissionMapper;
import com.fh.usertomenu.entity.MenuToUser;
import com.fh.usertomenu.entity.UmsUsertomenu;
import com.fh.usertomenu.mapper.UmsUsertomenuMapper;
import com.fh.usertomenu.service.IUmsUsertomenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lzq
 * @since 2021-05-17
 */
@Service
public class UmsUsertomenuServiceImpl extends ServiceImpl<UmsUsertomenuMapper, UmsUsertomenu> implements IUmsUsertomenuService {

    @Autowired
    private UmsPermissionMapper umsPermissionMapper;
    @Override
    public List<Map<String, Object>> UserToMenu() {
        List<UmsPermission> list = umsPermissionMapper.selectList(null);
        return getTree(list,0l);
    }

    @Override
    public void SaveMenuToUser(MenuToUser menuToUser) {
        QueryWrapper<UmsUsertomenu> queryWrapper=new QueryWrapper();
        queryWrapper.eq("userId",menuToUser.getUserId());
        remove(queryWrapper);
        if(CollectionUtils.isNotEmpty(menuToUser.getMenuIdList())){
            List<UmsUsertomenu> list=new ArrayList<>();
            menuToUser.getMenuIdList().forEach(menuId->{
                UmsUsertomenu usertomenu = new UmsUsertomenu();
                usertomenu.setUserid(menuToUser.getUserId());
                usertomenu.setMenuid(menuId);
                list.add(usertomenu);
            });
            saveBatch(list);
        }
    }

    private List<Map<String, Object>> getTree(List<UmsPermission> list, Long pid) {
        List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
        list.forEach(umsPermission -> {
            Map<String, Object> map = null;
            if (pid.equals(umsPermission.getPid())) {
                map = new HashMap<String, Object>();
                map.put("id", umsPermission.getId());
                map.put("value", umsPermission.getValue());
                map.put("type", umsPermission.getType());
                map.put("status", umsPermission.getStatus());
                map.put("name", umsPermission.getName());
                map.put("children", getTree(list, umsPermission.getId()));
            }
            if (map != null) {
                listMap.add(map);
            }
        });
        return listMap;
    }
}
