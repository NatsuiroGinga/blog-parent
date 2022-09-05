package com.mszlu.blog.handler;

import com.mszlu.blog.vo.Result;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.awt.image.RescaleOp;

/**
 * @author ginga
 * @version 1.0
 * @ClassName AllExceptionHandler
 * @Date 4/9/2022 上午10:13
 */
// 对加了@Controller注解的方法进行拦截处理
@ControllerAdvice
public class AllExceptionHandler {

    // 进行异常处理, 处理Exception.class 的异常
    @ExceptionHandler (Exception.class)
    @ResponseBody // 返回json数据
    public Result doException(@NotNull Exception e) {
        e.printStackTrace();
        return Result.fail(-999, "系统异常");
    }
}
