package com.visualclient;

import com.visualclient.gui.ClickGui;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;

public class VisualClientClient implements ClientModInitializer {
    public static ClickGui clickGui;

    @Override
    public void onInitializeClient() {
        clickGui = new ClickGui();
        
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (MinecraftClient.getInstance().player == null) return;
            
            // Right Shift key check (KEY_RIGHT_SHIFT)
            while (MinecraftClient.getInstance().options.rightShiftKey.wasPressed()) {
                if (clickGui.isOpen()) {
                    clickGui.close();
                } else {
                    clickGui.open();
                }
            }
        });
    }
}
