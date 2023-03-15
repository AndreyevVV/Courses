package main;

import ChatSocketServer.ChatSocketServer;
import ChatSocketServer.Flag;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * @author a.akbashev
 * @author v.chibrikov
 *         <p>
 *         Пример кода для курса на https://stepic.org/
 *         <p>
 *         Описание курса и лицензия: https://github.com/vitaly-chibrikov/stepic_java_webserver
 */
public class Main {
    static final Logger logger = LogManager.getLogger(Main.class.getName());

    public static void main(String[] args) throws Exception {

        Flag.INSTANCE.setShouldContinue(true);

        try {
            ServerSocket serverSocket = new ServerSocket(5050);
            serverSocket.setSoTimeout(100000);
            while (Flag.INSTANCE.getShouldContinue()) {
                ChatSocketServer server = new ChatSocketServer(serverSocket.accept());
                server.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        logger.info("Server started");
    }
}
