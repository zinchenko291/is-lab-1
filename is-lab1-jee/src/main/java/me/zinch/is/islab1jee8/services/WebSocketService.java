package me.zinch.is.islab1jee8.services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.websocket.Session;
import me.zinch.is.islab1jee8.models.ws.WebSocketResponse;
import me.zinch.is.islab1jee8.models.ws.WsAction;
import me.zinch.is.islab1jee8.models.ws.WsEntity;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@ApplicationScoped
public class WebSocketService {
    private final Set<Session> sessions = ConcurrentHashMap.newKeySet();

    public void add(Session session) {
        sessions.add(session);
    }

    public void remove(Session session) {
        sessions.remove(session);
    }

    public void sendEvent(WsEntity entity,
                          WsAction action,
                          Integer id,
                          Object payloadDto) {
        try (Jsonb builder = JsonbBuilder.create()) {
            WebSocketResponse<Object> event = new WebSocketResponse<>("event", entity.getValue(), action.getValue(), id, payloadDto);
            String json = builder.toJson(event);
            broadcast(json);
        } catch (Exception ignored) { }
    }

    private void broadcast(String jsonMessage) {
        sessions.forEach(s -> {
            try {
                s.getBasicRemote().sendText(jsonMessage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
