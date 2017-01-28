package com.acme.tictactoe;

import com.squareup.otto.Bus;

public class BusProvider {

    private static Bus instance;

    public static Bus getInstance() {
        if (instance == null) {
            instance = new Bus();
        }
        return instance;
    }

    public static void register(Object... args) {
        for (Object arg : args) {
            getInstance().register(arg);
        }
    }

    public static void unregister(Object... args) {
        for (Object arg : args) {
            getInstance().unregister(arg);
        }
    }
}
