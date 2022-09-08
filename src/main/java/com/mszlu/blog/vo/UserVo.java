package com.mszlu.blog.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

/**
 * @author ginga
 * @version 1.0
 * @ClassName UserVo
 * @Date 8/9/2022 上午9:18
 */
@Data
public class UserVo {

    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    private String nickname;

    private String avatar;

}
