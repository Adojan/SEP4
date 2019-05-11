import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.WebSocket;
import java.util.Scanner;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class Main {


    public static void main(String[] args) {

        testinter testinter = new test();
        System.out.println(testinter.get());


//        WebSocketClient listner = new WebSocketClient();
//        while (true) ;
//

////        WebSocketClient webClient = new WebSocketClient();
//        CharSequence payload= "{" +
//                "cmd: 'cq'"+
//                "}";
////        CompletableFuture<WebSocket> response = webClient.getSocket().sendText(payload, true);
////
//        WebSocketClient listener = new WebSocketClient();
//        HttpClient client = HttpClient.newHttpClient();
//        CompletableFuture<WebSocket> websocket = client.newWebSocketBuilder()
//                .buildAsync(URI.create("wss://iotnet.teracom.dk/app?token=vnoRjwAAABFpb3RuZXQudGVyYWNvbS5ka79QaSF5zzp-QuFIXpC6XZQ="), listener);
//        try {
//            websocket.get().sendText(payload, false);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }
//        new Scanner(System.in);
    }

}

