package com.fw.protocol.impl;

import com.fw.protocol.HelloService;
import org.apache.thrift.TException;

/**
 * Created by Administrator on 2016/8/4/0004.
 */
public class HelloServiceImpl implements HelloService.Iface{

    public HelloServiceImpl() {}


    public String sayHello(String username) throws TException {
        return "Hello, " + username + "! Welcome to my thrift program!";
    }
}
