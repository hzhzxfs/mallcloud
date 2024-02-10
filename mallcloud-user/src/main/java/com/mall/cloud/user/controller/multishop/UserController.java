package com.mall.cloud.user.controller.multishop;

import com.mall.cloud.api.user.vo.UserApiVO;
import com.mall.cloud.common.database.vo.PageVO;
import com.mall.cloud.user.model.User;
import com.mall.cloud.user.service.UserService;
import com.mall.cloud.user.dto.UserDTO;
import com.mall.cloud.common.database.dto.PageDTO;
import com.mall.cloud.common.response.ServerResponseEntity;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import com.mall.cloud.common.util.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

//用户表

@RestController("multishopUserController")
@RequestMapping("/m/user")
@Tag(name = "店铺-用户表")
public class UserController {

    @Autowired
    private UserService userService;



	@GetMapping("/page")
	@Operation(summary = "获取用户表列表" , description = "分页获取用户表列表")
	public ServerResponseEntity<PageVO<UserApiVO>> page(@Valid PageDTO pageDTO) {
		PageVO<UserApiVO> userPage = userService.page(pageDTO);
		return ServerResponseEntity.success(userPage);
	}

	@GetMapping
    @Operation(summary = "获取用户表" , description = "根据userId获取用户表")
    public ServerResponseEntity<UserApiVO> getByUserId(@RequestParam Long userId) {
        UserApiVO userVO = BeanUtil.map(userService.getByUserId(userId), UserApiVO.class);
        return ServerResponseEntity.success(userVO);
    }


    @PutMapping
    @Operation(summary = "更新用户表" , description = "更新用户表")
    public ServerResponseEntity<Void> update(@Valid @RequestBody UserDTO userDTO) {
        User user = BeanUtil.map(userDTO, User.class);
        userService.update(user);
        return ServerResponseEntity.success();
    }

}
