package fr.websocket.encrypt;

import fr.websocket.entity.AdminCard;
import fr.websocket.entity.Message;

import javax.json.Json;
import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

public class AdminEncoder implements Encoder.Text<AdminCard> {
    @Override
    public String encode(final AdminCard card) throws EncodeException {
        return Json.createObjectBuilder()
                .add("status", card.getStatus())
                .add("message", card.getMessage())
                .add("card_id", card.getCardId())
                .build().toString();
    }

    @Override
    public void init(EndpointConfig endpointConfig) {

    }

    @Override
    public void destroy() {

    }
}
