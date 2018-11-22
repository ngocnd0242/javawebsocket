package fr.websocket.entity;

import lombok.Data;

@Data
public class AdminCard {
    private int status;
    private String message;
    private String cardId;
}
