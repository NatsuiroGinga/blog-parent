package com.mszlu.blogadmin.vo;

import com.sun.istack.internal.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.jetbrains.annotations.Contract;

/**
 * @author ginga
 * @version 1.0
 * @ClassName Result
 * @Date 11/9/2022 下午7:48
 */
@Data
@AllArgsConstructor
public class Result {

    private boolean success;

    private int code;

    private String msg;

    private Object data;

    @org.jetbrains.annotations.NotNull
    @NotNull
    @Contract("_ -> new")
    public static Result success(Object data) {
        return new Result(true, 200, "success", data);
    }

    @org.jetbrains.annotations.NotNull
    @NotNull
    @Contract("_, _ -> new")
    public static Result fail(int code, String msg) {
        return new Result(false, code, msg, null);
    }

}
