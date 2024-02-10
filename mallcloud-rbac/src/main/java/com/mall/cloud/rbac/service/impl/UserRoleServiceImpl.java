package com.mall.cloud.rbac.service.impl;

import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import com.mall.cloud.rbac.mapper.UserRoleMapper;
import com.mall.cloud.rbac.service.UserRoleService;


@Service
public class UserRoleServiceImpl implements UserRoleService {

	@Resource
	private UserRoleMapper userRoleMapper;

}
