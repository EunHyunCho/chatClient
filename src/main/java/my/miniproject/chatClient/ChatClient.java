package my.miniproject.chatClient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

public class ChatClient {
    private String ip;
    private int portNum;

    public ChatClient(String ip, int portNum) {
        this.ip = ip;
        this.portNum = portNum;
    }

    public void run(){
        Socket socket = null;
        ChatUser chatUser = null;
        BufferedReader br = null;

        try{
            br = new BufferedReader(new InputStreamReader(System.in));
            socket = new Socket(ip, portNum);

            System.out.println("닉네임을 입력하세요(수정불가): ");
            String nickname = br.readLine();
            chatUser = new ChatUser(nickname, socket);

            ChatClientHandler chatClientHandler = new ChatClientHandler(chatUser);
            chatClientHandler.start();

            while(true){
                String message = br.readLine();
                chatUser.write(message);
            }

        }catch(Exception e){
            System.out.println("연결이 끊어졌습니다.");
        }finally {
            chatUser.close();
        }



    }
}