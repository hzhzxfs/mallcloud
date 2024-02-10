package com.mall.cloud.rbac.service.impl;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import com.mall.cloud.rbac.mapper.RoleMenuMapper;
import com.mall.cloud.rbac.service.RoleMenuService;


@Service
public class RoleMenuServiceImpl implements RoleMenuService {

	@Resource
	private RoleMenuMapper roleMenuMapper;

}
