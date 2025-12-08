package me.zinch.is.islab1jee8.models.ws;

public enum WsAction {
    CREATE("CREATE"),
    UPDATE("UPDATE"),
    DELETE("DELETE")
    ;

    private final String value;

    private WsAction(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
