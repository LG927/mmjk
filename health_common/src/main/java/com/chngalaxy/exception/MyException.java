package com.chngalaxy.exception;


/**
 * <p>
 * 自定义异常
 给用户友好提示
 区分是业务还是系统异常
 终止已知不符合业务逻辑代码的继续执行
 * </p>
 *
 * @author: Eric
 * @since: 2021/2/4
 */
public class MyException extends RuntimeException {

    /**
     * 构建 就要传入提示的信息
     * @param message
     */
    public MyException(String message){
        super(message);
    }
}
