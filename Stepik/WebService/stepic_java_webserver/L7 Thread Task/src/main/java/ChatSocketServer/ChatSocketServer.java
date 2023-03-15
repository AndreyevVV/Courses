package ChatSocketServer;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
public class ChatSocketServer extends Thread{
    private final Socket socket;
    public ChatSocketServer(Socket  socket) throws IOException
    {
        this.socket = socket;
    }

    @Override
    public void run()
    {
        try
        {
            DataInputStream in = new DataInputStream(socket.getInputStream());
            String message = in.readUTF();
            java.util.logging.Logger.getGlobal().info(message);
            if (!message.equals("Bue."))
            {
                DataOutputStream out = new DataOutputStream(socket.getOutputStream());
                out.writeUTF(message);
                out.flush();
            }
            else
            {
                Flag.INSTANCE.setShouldContinue(false);
            }
            in.close();
            socket.close();
        }
        catch (IOException io)
        {
            io.printStackTrace();
        }
    }
}
