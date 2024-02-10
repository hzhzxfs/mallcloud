package com.mall.cloud.rbac.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.mall.cloud.api.rbac.dto.UserRoleDTO;
import com.mall.cloud.api.rbac.feign.UserRoleFeignClient;
import com.mall.cloud.common.cache.constant.CacheNames;
import com.mall.cloud.common.response.ServerResponseEntity;
import com.mall.cloud.rbac.mapper.UserRoleMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class UserRoleFeignController implements UserRoleFeignClient {

	private static final Logger logger = LoggerFactory.getLogger(UserRoleFeignController.class);

	@Autowired
	private UserRoleMapper userRoleMapper;

	@Override
	@Transactional(rollbackFor = Exception.class)
	@CacheEvict(cacheNames = CacheNames.MENU_ID_LIST_KEY, key = "#userRoleDTO.userId")
	public ServerResponseEntity<Void> saveByUserIdAndSysType(UserRoleDTO userRoleDTO) {

		if(CollUtil.isEmpty(userRoleDTO.getRoleIds())){
			return ServerResponseEntity.success();
		}
		//保存用户与角色关系
		userRoleMapper.insertUserAndUserRole(userRoleDTO.getUserId(), userRoleDTO.getRoleIds());
		return ServerResponseEntity.success();
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	@CacheEvict(cacheNames = CacheNames.MENU_ID_LIST_KEY, key = "#userRoleDTO.userId")
	public ServerResponseEntity<Void> updateByUserIdAndSysType(UserRoleDTO userRoleDTO) {
		//先删除用户与角色关系
		userRoleMapper.deleteByUserId(userRoleDTO.getUserId());

		if(CollUtil.isEmpty(userRoleDTO.getRoleIds())){
			return ServerResponseEntity.success();
		}
		//保存用户与角色关系
		userRoleMapper.insertUserAndUserRole(userRoleDTO.getUserId(), userRoleDTO.getRoleIds());
		return ServerResponseEntity.success();
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	@CacheEvict(cacheNames = CacheNames.MENU_ID_LIST_KEY, key = "#userId")
	public ServerResponseEntity<Void> deleteByUserIdAndSysType(Long userId) {
		userRoleMapper.deleteByUserId(userId);
		return ServerResponseEntity.success();
	}

	@Override
	public ServerResponseEntity<List<Long>> getRoleIds(Long userId) {
		return ServerResponseEntity.success(userRoleMapper.getRoleIds(userId));
	}
}
