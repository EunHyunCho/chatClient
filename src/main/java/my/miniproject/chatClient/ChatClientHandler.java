package my.miniproject.chatClient;

public class ChatClientHandler extends Thread{
    private ChatUser chatUser;

    public ChatClientHandler(ChatUser chatUser) {
        this.chatUser = chatUser;
    }

    public void run(){
        while(true){
            String message = chatUser.read();
            System.out.println(message);
        }
    }
}
