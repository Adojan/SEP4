import controller.WebSocketClient;

public class Main {


    public static void main(String[] args) throws Exception {


        WebSocketClient webSocketClient = new WebSocketClient();
        while (true) {

        }
    }


//        try {
//            MongoDBClient mongoDBClient = new MongoDBClient();
//            mongoDBClient.setData("Measurements");
//        } catch (MongoSecurityException mse) {
//            System.err.println(">>" + mse.getClass().getName() + ": " + mse.getMessage());
//            JOptionPane.showMessageDialog(null, "Invalid Credentials, login denied, security");
//        }


//        controller.WebSocketClient listner = new controller.WebSocketClient();
//        while (true) ;
//

////        controller.WebSocketClient webClient = new controller.WebSocketClient();
//        CharSequence payload= "{" +
//                "cmd: 'cq'"+
//                "}";
////        CompletableFuture<WebSocket> response = webClient.getSocket().sendText(payload, true);
////
//        controller.WebSocketClient listener = new controller.WebSocketClient();
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



