package com.mall.cloud.user.vo;

import io.swagger.v3.oas.annotations.media.Schema;


public class UserSimpleInfoVO {

    @Schema(description = "用户昵称" ,requiredMode = Schema.RequiredMode.REQUIRED)
    private String nickName;

    @Schema(description = "用户头像" ,requiredMode = Schema.RequiredMode.REQUIRED)
    private String pic;

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    @Override
    public String toString() {
        return "UserCenterInfoVO{" +
                "nickName='" + nickName + '\'' +
                ", pic='" + pic + '\'' +
                '}';
    }
}
