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

    public static void main(String[] args) throws FtpException {
        org.apache.log4j.BasicConfigurator.configure();
        FtpServerFactory serverFactory = new FtpServerFactory();

        ListenerFactory factory = new ListenerFactory();

        factory.setPort(2121);

        serverFactory.addListener("default", factory.createListener());

        BaseUser user = new BaseUser();
        user.setName("admin");
        user.setPassword("12345");

        user.setHomeDirectory(serverPath);

        List<Authority> authorities = new ArrayList<Authority>();

        authorities.add(new WritePermission());
        user.setAuthorities(authorities);

        serverFactory.getUserManager().save(user);

        FtpServer server = serverFactory.createServer();
        server.start();
    }
}
