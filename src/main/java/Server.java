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
// Установить порт прослушивания
        factory.setPort(2121);

// Заменим прослушиватель по умолчанию
        serverFactory.addListener("default", factory.createListener());

// имя пользователя
        BaseUser user = new BaseUser();
        user.setName("admin");
// пароль Если вы не установили пароль, вы являетесь анонимным пользователем
        user.setPassword("12345");
// домашний каталог пользователя
        user.setHomeDirectory(serverPath);

        List<Authority> authorities = new ArrayList<Authority>();
// увеличить разрешение на запись
        authorities.add(new WritePermission());
        user.setAuthorities(authorities);

// Добавить пользователя
        serverFactory.getUserManager().save(user);

        /**
         Вы также можете использовать файлы конфигурации для управления пользователями
         */
//      PropertiesUserManagerFactory userManagerFactory = new PropertiesUserManagerFactory();
//      userManagerFactory.setFile(new File("users.properties"));
//      serverFactory.setUserManager(userManagerFactory.createUserManager());

        FtpServer server = serverFactory.createServer();
        server.start();
    }
}
