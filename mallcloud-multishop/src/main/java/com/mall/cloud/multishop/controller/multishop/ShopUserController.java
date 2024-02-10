package com.mall.cloud.multishop.controller.multishop;

import com.mall.cloud.api.auth.bo.UserInfoInTokenBO;
import com.mall.cloud.api.multishop.vo.ShopDetailVO;
import com.mall.cloud.common.database.dto.PageDTO;
import com.mall.cloud.common.database.vo.PageVO;
import com.mall.cloud.common.response.ResponseEnum;
import com.mall.cloud.common.response.ServerResponseEntity;
import com.mall.cloud.common.security.AuthUserContext;
import com.mall.cloud.multishop.dto.ShopUserDTO;
import com.mall.cloud.multishop.model.ShopUser;
import com.mall.cloud.multishop.service.ShopDetailService;
import com.mall.cloud.multishop.service.ShopUserService;
import com.mall.cloud.multishop.vo.ShopUserVO;
import com.mall.cloud.multishop.vo.ShopUserSimpleVO;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import com.mall.cloud.common.util.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.Objects;


@RequestMapping(value = "/m/shop_user")
@RestController("multishopShopUserController")
@Tag(name = "店铺用户信息")
public class ShopUserController {

	@Autowired
	private ShopUserService shopUserService;

	@Autowired
	private ShopDetailService shopDetailService;


	@GetMapping("/info")
	@Operation(summary = "登陆店铺用户信息" , description = "获取当前登陆店铺用户的用户信息")
	public ServerResponseEntity<ShopUserSimpleVO> info() {
		UserInfoInTokenBO userInfoInTokenBO = AuthUserContext.get();
		ShopUserSimpleVO shopUserSimple = new ShopUserSimpleVO();
		shopUserSimple.setIsAdmin(userInfoInTokenBO.getIsAdmin());
		ShopDetailVO shopDetail = shopDetailService.getByShopId(userInfoInTokenBO.getTenantId());
		shopUserSimple.setAvatar(shopDetail.getShopLogo());
		shopUserSimple.setNickName(shopDetail.getShopName());
		return ServerResponseEntity.success(shopUserSimple);
	}

	@GetMapping("/page")
	@Operation(summary = "店铺用户列表" , description = "获取店铺用户列表")
	public ServerResponseEntity<PageVO<ShopUserVO>> page(@Valid PageDTO pageDTO, String nickName) {
		UserInfoInTokenBO userInfoInTokenBO = AuthUserContext.get();
		PageVO<ShopUserVO> shopUserPage = shopUserService.pageByShopId(pageDTO, userInfoInTokenBO.getTenantId(), nickName);
		return ServerResponseEntity.success(shopUserPage);
	}

	@GetMapping
	@Operation(summary = "获取店铺用户信息" , description = "根据用户id获取店铺用户信息")
	public ServerResponseEntity<ShopUserVO> detail(@RequestParam Long shopUserId) {
		return ServerResponseEntity.success(shopUserService.getByUserId(shopUserId));
	}

	@PostMapping
	@Operation(summary = "保存店铺用户信息" , description = "保存店铺用户信息")
	public ServerResponseEntity<Void> save(@Valid @RequestBody ShopUserDTO shopUserDTO) {
		ShopUser shopUser = BeanUtil.map(shopUserDTO, ShopUser.class);
		shopUser.setShopUserId(null);
		shopUser.setShopId(AuthUserContext.get().getTenantId());
		shopUser.setHasAccount(0);
		shopUserService.save(shopUser,shopUserDTO.getRoleIds());
		return ServerResponseEntity.success();
	}

	@PutMapping
	@Operation(summary = "更新店铺用户信息" , description = "更新店铺用户信息")
	public ServerResponseEntity<Void> update(@Valid @RequestBody ShopUserDTO shopUserDTO) {
		ShopUser shopUser = BeanUtil.map(shopUserDTO, ShopUser.class);
		ShopUserVO dbShopUser = shopUserService.getByUserId(shopUserDTO.getShopUserId());
		if (!Objects.equals(dbShopUser.getShopId(), AuthUserContext.get().getTenantId())) {
			return ServerResponseEntity.fail(ResponseEnum.UNAUTHORIZED);
		}
		shopUser.setShopId(dbShopUser.getShopId());
		shopUserService.update(shopUser,shopUserDTO.getRoleIds());
		return ServerResponseEntity.success();
	}

	@DeleteMapping
	@Operation(summary = "删除店铺用户信息" , description = "根据店铺用户id删除店铺用户信息")
	public ServerResponseEntity<Void> delete(@RequestParam Long shopUserId) {
		ShopUserVO dbShopUser = shopUserService.getByUserId(shopUserId);
		if (!Objects.equals(dbShopUser.getShopId(), AuthUserContext.get().getTenantId())) {
			return ServerResponseEntity.fail(ResponseEnum.UNAUTHORIZED);
		}
		shopUserService.deleteById(shopUserId);
		return ServerResponseEntity.success();
	}
}
