package fr.websocket.endpoint;

import fr.websocket.encrypt.AdminDecoder;
import fr.websocket.encrypt.AdminEncoder;
import fr.websocket.entity.AdminCard;
import fr.websocket.entity.Message;
import fr.websocket.encrypt.MessageDecoder;
import fr.websocket.encrypt.MessageEncoder;

import javax.websocket.*;
import java.io.IOException;
import java.util.*;

import static java.lang.String.format;

@javax.websocket.server.ServerEndpoint(value = "/admin", encoders = AdminEncoder.class, decoders = AdminDecoder.class)
public class AdminEndpoint {

    static Set<Session> peers = Collections.synchronizedSet(new HashSet<Session>());

    @OnOpen
    public void onOpen(Session session) throws IOException, EncodeException {
        System.out.println(format("%s joined the check admin.", session.getId()));
        peers.add(session);

        AdminCard adminCard = new AdminCard();
        adminCard.setStatus(200);
        adminCard.setMessage("connect success!");
        session.getBasicRemote().sendObject(adminCard);
    }

    @OnMessage
    public void onMessage(AdminCard message, Session session) throws IOException, EncodeException {
        //broadcast the message
        for (Session peer : peers) {
            if (!session.getId().equals(peer.getId())) { // do not resend the message to its sender
                peer.getBasicRemote().sendObject(message);
            }
        }
    }

    @OnClose
    public void onClose(Session session) throws IOException, EncodeException {
        System.out.println(format("%s left the check admin.", session.getId()));
        peers.remove(session);
    }

    @OnError
    public void onError(Throwable t, Session session) throws Throwable{
        if(Objects.isNull(session)){
            System.err.println("connection error: " + session.getId());
        }
        AdminCard adminCard = new AdminCard();
        adminCard.setStatus(500);
        adminCard.setMessage("connect error!");
        session.getBasicRemote().sendObject(adminCard);
    }
}
