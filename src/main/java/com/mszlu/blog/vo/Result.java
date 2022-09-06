package com.mszlu.blog.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/**
 * @author ginga
 * @version 1.0
 * @ClassName Result
 * @Date 3/7/2022 下午11:55
 */
@Data
@AllArgsConstructor
public class Result {

    private boolean success;

    private int code;

    private String msg;

    private Object data;

    @NotNull
    @Contract("_ -> new")
    public static Result success(Object data) {
        return new Result(true, 200, "success", data);
    }

    @NotNull
    @Contract("_, _ -> new")
    public static Result fail(int code, String msg) {
        return new Result(false, code, msg, null);
    }

}
