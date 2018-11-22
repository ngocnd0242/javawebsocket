package fr.websocket;

import fr.websocket.endpoint.AdminEndpoint;
import fr.websocket.endpoint.ClientEndpoint;
import fr.websocket.endpoint.ProductEndpoint;
import fr.websocket.endpoint.PropsEndpoint;

import javax.websocket.DeploymentException;
import java.util.Scanner;

public class Server {
    public static void main(String[] args) {
        org.glassfish.tyrus.server.Server server = new org.glassfish.tyrus.server.Server
                ("localhost", 8025, "/ws", null,
                        AdminEndpoint.class,
                        PropsEndpoint.class,
                        ClientEndpoint.class,
                        ProductEndpoint.class
                );
        try {
            server.start();
            System.out.println("Press any key to stop the server..");
            new Scanner(System.in).nextLine();
        } catch (DeploymentException e) {
            throw new RuntimeException(e);
        } finally {
            server.stop();
        }
    }
}
