package com.fh.usertomenu.service;

import com.fh.usertomenu.entity.MenuToUser;
import com.fh.usertomenu.entity.UmsUsertomenu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lzq
 * @since 2021-05-17
 */
public interface IUmsUsertomenuService extends IService<UmsUsertomenu> {

    List<Map<String, Object>> UserToMenu();

    void SaveMenuToUser(MenuToUser menuToUser);
}
