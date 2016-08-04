package com.fw.server;

import com.fw.protocol.HelloService;
import com.fw.protocol.impl.HelloServiceImpl;
import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TTransportException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import sun.rmi.runtime.Log;

import java.util.logging.Logger;

/**
 * Created by Administrator on 2016/8/4/0004.
 */
public class HelloServer {
    public static Logger log = Logger.getLogger(HelloServer.class.getName());

    public int SERVER_PORT;

    public HelloServer(int port) {
        this.SERVER_PORT = port;
    }

    public void start() throws TTransportException {
        log.info("server start ..... ");
        TProcessor tprocessor = new HelloService.Processor<HelloServiceImpl>(new HelloServiceImpl());
        TServerSocket socket = new TServerSocket(SERVER_PORT);
        TServer.Args tArgs = new TServer.Args(socket);
        tArgs.processor(tprocessor);
        tArgs.protocolFactory(new TBinaryProtocol.Factory());

        TServer server = new TSimpleServer(tArgs);
        server.serve();

    }

    public void setSERVER_PORT(int SERVER_PORT) {
        this.SERVER_PORT = SERVER_PORT;
    }

    public int getSERVER_PORT() {
        return SERVER_PORT;
    }

    public static void main(String[] args) {
        ApplicationContext context = new FileSystemXmlApplicationContext("applicationContext.xml");
        HelloServer server  = (HelloServer)context.getBean("server");
        try {
            server.start();
        } catch (TTransportException e) {
            e.printStackTrace();
        }
    }

}
