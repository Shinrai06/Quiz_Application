package com.example.partb.listeners;

import javafx.event.EventHandler;
import javafx.scene.Node;

public interface NewScreenListener extends EventHandler {
    void changeScreen(Node node);
    void removeTopScreen();
}
