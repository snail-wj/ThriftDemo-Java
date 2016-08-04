package com.fw.client;

import com.fw.protocol.HelloService;
import javafx.application.Application;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import java.util.logging.Logger;

/**
 * Created by Administrator on 2016/8/4/0004.
 */
public class HelloClient {

    public static Logger log = Logger.getLogger(HelloClient.class.getName());

    public String SERVER_IP;

    public int SERVER_PORT;

    public int TIME_OUT;

    public TTransport transport;

    public HelloClient() {}

    public HelloClient(String ip, int port) {
        this.SERVER_IP = ip;
        this.SERVER_PORT = port;

    }

    public String getSERVER_IP() {
        return SERVER_IP;
    }

    public void setSERVER_IP(String SERVER_IP) {
        this.SERVER_IP = SERVER_IP;
    }

    public int getSERVER_PORT() {
        return SERVER_PORT;
    }

    public void setSERVER_PORT(int SERVER_PORT) {
        this.SERVER_PORT = SERVER_PORT;
    }

    public int getTIME_OUT() {
        return TIME_OUT;
    }

    public void setTIME_OUT(int TIME_OUT) {
        this.TIME_OUT = TIME_OUT;
    }

    public void start(String username) throws TException {
        this.transport = new TSocket(this.SERVER_IP, this.SERVER_PORT, TIME_OUT);
//        协议需要和服务器一致
        TProtocol protocol = new TBinaryProtocol(transport);
        HelloService.Client client = new HelloService.Client(protocol);
        transport.open();
        String result = client.sayHello(username);
        log.info(result);
    }

    public static void main(String[] args) {
        ApplicationContext context = new FileSystemXmlApplicationContext("applicationContext.xml");
        HelloClient client = (HelloClient)context.getBean("client");
        try {
            client.start("fengwei");
        } catch (TException e) {
            e.printStackTrace();
        }

    }

}
