package my.miniproject.chatClient;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class ChatUser {
    private String nickName;
    private Socket socket;
    private DataInputStream in = null;
    private DataOutputStream out = null;

    public ChatUser(String nickName, Socket socket) {
        this.nickName = nickName;
        this.socket = socket;
        try {
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
        }catch(Exception e){
            System.out.println("생성오류");
        }
    }

    public void close(){
        try{in.close();}catch (Exception e){}
        try{out.close();}catch (Exception e){}
        try{socket.close();}catch (Exception e){}
    }

    public String getNickName() {
        return nickName;
    }

    public void write(String message) {
       try{
           out.writeUTF(message);
           out.flush();
       }catch (Exception e){
           System.out.println("메세지 전송오류");
       }
    }

    public String read() {

        try{
          return in.readUTF();
        }catch (Exception e) {
            throw new RuntimeException("메세지 읽기오류");
        }
    }
}
