package fr.websocket.encrypt;

import fr.websocket.entity.AdminCard;
import fr.websocket.entity.Message;

import javax.json.Json;
import javax.json.JsonObject;
import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;
import java.io.StringReader;
import java.util.Date;

public class AdminDecoder implements Decoder.Text<AdminCard> {
    @Override
    public AdminCard decode(final String textMessage) throws DecodeException {
        AdminCard adminCard = new AdminCard();
        JsonObject jsonObject = Json.createReader(new StringReader(textMessage)).readObject();
        adminCard.setStatus(jsonObject.getInt("status"));
        adminCard.setMessage(jsonObject.getString("message"));
        adminCard.setCardId(jsonObject.getString("card_id"));
        return adminCard;
    }

    @Override
    public boolean willDecode(String s) {
        return false;
    }

    @Override
    public void init(EndpointConfig endpointConfig) {

    }

    @Override
    public void destroy() {

    }
}
