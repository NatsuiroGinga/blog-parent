package com.mszlu.blog.vo;

import lombok.Data;

/**
 * @author ginga
 * @version 1.0
 * @ClassName LoginUserVo
 * @Date 5/9/2022 下午10:14
 */
@Data
public class LoginUserVo {

    private Long id;

    private String account;

    private String nickname;

    private String avatar;

}
