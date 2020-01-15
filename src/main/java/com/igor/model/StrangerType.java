package com.igor.model;

public enum StrangerType {
    GOOD("good", "help you if you need"),
    WARRIOR("warrior", "kill you if you stay on his way"),
    HIPPIE("hippie", "smoke a weed with you");

    private String type;
    private String description;

    StrangerType(String type, String description) {
        this.type = type;
        this.description = description;
    }

    @Override
    public String toString() {
        return "type=" + type + ", description=" + description;
    }
}
