package com.lxgzhw.dao;

import com.lxgzhw.domain.Admin;

public interface AdminDao {
    /**
     * 登录功能
     */
    Admin login(Admin loginUser);
}
