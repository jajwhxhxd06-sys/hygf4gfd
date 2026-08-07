package com.visualclient.gui;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;

import java.util.ArrayList;
import java.util.List;

public class ClickGui extends Screen {
    private static final MinecraftClient mc = MinecraftClient.getInstance();
    private boolean isOpen = false;
    private List<ModuleButton> moduleButtons;
    private int selectedCategory = 0;
    
    // GUI Colors (Custom non-vanilla style)
    private int backgroundColor = 0x99000000;
    private int accentColor = 0xFF0066CC;
    private int textColor = 0xFFFFFFFF;
    private int buttonBackgroundColor = 0x77000000;
    
    public ClickGui() {
        super(Text.literal("Click GUI"));
        this.moduleButtons = new ArrayList<>();
        initModules();
    }
    
    private void initModules() {
        // Add example modules/categories
        moduleButtons.add(new ModuleButton("Combat", 50, 50));
        moduleButtons.add(new ModuleButton("Render", 50, 100));
        moduleButtons.add(new ModuleButton("Movement", 50, 150));
        moduleButtons.add(new ModuleButton("Player", 50, 200));
        moduleButtons.add(new ModuleButton("World", 50, 250));
    }
    
    public void open() {
        if (mc.player != null) {
            mc.setScreen(this);
            isOpen = true;
        }
    }
    
    public void close() {
        if (mc.player != null) {
            mc.setScreen(null);
            isOpen = false;
        }
    }
    
    public boolean isOpen() {
        return isOpen;
    }
    
    @Override
    protected void init() {
        super.init();
    }
    
    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        // Render custom background with gradient effect
        renderBackground(context, mouseX, mouseY, delta);
        
        // Draw title
        context.drawTextWithShadow(mc.textRenderer, "VisualClient GUI", width / 2 - 40, 20, accentColor);
        
        // Render module buttons
        for (ModuleButton button : moduleButtons) {
            button.render(context, mouseX, mouseY, delta);
        }
        
        // Draw instructions
        context.drawTextWithShadow(mc.textRenderer, "Right Shift to close", 10, height - 20, 0x88FFFFFF);
        
        super.render(context, mouseX, mouseY, delta);
    }
    
    @Override
    public void renderBackground(DrawContext context, int mouseX, int mouseY, float delta) {
        // Custom dark gradient background
        context.fillGradient(0, 0, width, height, 0xCC1a1a2e, 0xCC0d0d1a);
        
        // Draw accent border
        context.fill(0, 0, width, 3, accentColor);
        context.fill(0, height - 3, width, height, accentColor);
        context.fill(0, 0, 3, height, accentColor);
        context.fill(width - 3, 0, width, height, accentColor);
    }
    
    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        for (ModuleButton moduleButton : moduleButtons) {
            if (moduleButton.isMouseOver((int)mouseX, (int)mouseY)) {
                moduleButton.onClick(button);
                return true;
            }
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }
    
    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (keyCode == GLFW.GLFW_KEY_RIGHT_SHIFT || keyCode == GLFW.GLFW_KEY_LEFT_SHIFT) {
            close();
            return true;
        }
        return super.keyPressed(keyCode, scanCode, modifiers);
    }
    
    @Override
    public boolean shouldPause() {
        return false;
    }
    
    @Override
    public boolean shouldCloseOnEsc() {
        return true;
    }
}
