package com.gy.app;

import com.gy.annotation.AddHelloWorld;
import com.gy.annotation.Getter;
import com.gy.annotation.Setter;
import com.gy.annotation.ShowHelloWorld;

/**
 * @author guoyang
 * @date 2022/10/31 6:17 下午
 */
@Getter
@Setter
@ShowHelloWorld
public class MyApp {
private String value;
@AddHelloWorld
public static void main(String[] args){
    MyApp app = new MyApp();



}

}
