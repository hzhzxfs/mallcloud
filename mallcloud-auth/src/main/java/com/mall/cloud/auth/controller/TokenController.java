package com.mall.cloud.auth.controller;

import com.mall.cloud.common.security.bo.TokenInfoBO;
import com.mall.cloud.auth.dto.RefreshTokenDTO;
import com.mall.cloud.auth.manager.TokenStore;
import com.mall.cloud.api.auth.vo.TokenInfoVO;
import com.mall.cloud.common.response.ServerResponseEntity;
import io.swagger.v3.oas.annotations.tags.Tag;
import com.mall.cloud.common.util.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@Tag(name = "token")
public class TokenController {

	@Autowired
	private TokenStore tokenStore;


	@PostMapping("/ua/token/refresh")
	public ServerResponseEntity<TokenInfoVO> refreshToken(@Valid @RequestBody RefreshTokenDTO refreshTokenDTO) {
		ServerResponseEntity<TokenInfoBO> tokenInfoServerResponseEntity = tokenStore
				.refreshToken(refreshTokenDTO.getRefreshToken());
		if (!tokenInfoServerResponseEntity.isSuccess()) {
			return ServerResponseEntity.transform(tokenInfoServerResponseEntity);
		}
		return ServerResponseEntity
				.success(BeanUtil.map(tokenInfoServerResponseEntity.getData(), TokenInfoVO.class));
	}

}
