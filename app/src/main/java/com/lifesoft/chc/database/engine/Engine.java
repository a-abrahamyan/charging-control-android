package com.lifesoft.chc.database.engine;

public class Engine {
    private static Engine INSTANCE = null;

    private Engine() {
    }

    public static Engine INSTANCE() {
        return INSTANCE == null ? INSTANCE = new Engine() : INSTANCE;
    }
}
