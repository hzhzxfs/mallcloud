package com.mall.cloud.user.feign;

import com.mall.cloud.api.user.feign.UserAddrFeignClient;
import com.mall.cloud.common.order.vo.UserAddrVO;
import com.mall.cloud.common.response.ServerResponseEntity;
import com.mall.cloud.common.security.AuthUserContext;
import com.mall.cloud.user.service.UserAddrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

//用户地址feign连接

@RestController
public class UserAddrFeignController implements UserAddrFeignClient {

    @Autowired
    private UserAddrService userAddrService;

    @Override
    public ServerResponseEntity<UserAddrVO> getUserAddrByAddrId(Long addrId) {
        return ServerResponseEntity.success(userAddrService.getUserAddrByUserId(addrId,AuthUserContext.get().getUserId()));
    }
}
