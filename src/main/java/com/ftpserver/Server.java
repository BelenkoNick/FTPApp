package com.ftpserver;

import org.apache.ftpserver.FtpServer;
import org.apache.ftpserver.FtpServerFactory;
import org.apache.ftpserver.ftplet.Authority;
import org.apache.ftpserver.ftplet.FtpException;
import org.apache.ftpserver.listener.ListenerFactory;
import org.apache.ftpserver.usermanager.impl.BaseUser;
import org.apache.ftpserver.usermanager.impl.WritePermission;

import java.util.ArrayList;
import java.util.List;

public class Server {

    public static String serverPath = "E:/FTPServer";

    public static FtpServer start() throws FtpException {
        // basic configuration for listener and server
        org.apache.log4j.BasicConfigurator.configure();
        FtpServerFactory serverFactory = new FtpServerFactory();
        ListenerFactory factory = new ListenerFactory();

        // sets port for listener
        factory.setPort(2121);
        // adds listener as default to server
        serverFactory.addListener("default", factory.createListener());
        // adds username and password
        BaseUser user = new BaseUser();
        user.setName("admin");
        user.setPassword("12345");
        // sets local-to-server folder
        user.setHomeDirectory(serverPath);
        // creates write permission for user
        List<Authority> authorities = new ArrayList<>();
        authorities.add(new WritePermission());
        user.setAuthorities(authorities);
        // saves user
        serverFactory.getUserManager().save(user);

        // starts server
        FtpServer server = serverFactory.createServer();
        server.start();
        return server;

    }

    public static void stop(FtpServer server) {
        // shuts down the server
        server.stop();
    }
}
