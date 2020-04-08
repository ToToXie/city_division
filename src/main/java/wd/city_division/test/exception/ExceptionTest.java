package wd.city_division.test.exception;

import wd.city_division.exception.FebsException;
import wd.city_division.exception.FileDownloadException;

/**
 * @program: city_division
 * @description: 测试检查异常与非检查异常
 * @author: wd
 * @create: 2020-04-08 17:59
 **/

public class ExceptionTest {
    public static void main(String[] args) {
        ExceptionTest exceptionTest = new ExceptionTest();
        try {
            exceptionTest.testRuntimeException();
        } catch (Exception e) {
            e.printStackTrace();
        }
        /*try {
            exceptionTest.testException();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }*/

    }
    public void testException() throws Throwable{
        throw new FileDownloadException("检查异常");
    }
    public void testRuntimeException(){
        throw new FebsException("非检查异常");
    }
}
