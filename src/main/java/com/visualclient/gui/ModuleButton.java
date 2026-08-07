package com.visualclient.gui;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.RenderLayer;

public class ModuleButton {
    private static final MinecraftClient mc = MinecraftClient.getInstance();
    
    private String name;
    private int x, y;
    private int width = 150;
    private int height = 30;
    private boolean enabled = false;
    private boolean isHovered = false;
    
    // Custom colors
    private int normalColor = 0x55000000;
    private int hoverColor = 0x770066CC;
    private int enabledColor = 0xFF00AA00;
    private int textColor = 0xFFFFFFFF;
    
    public ModuleButton(String name, int x, int y) {
        this.name = name;
        this.x = x;
        this.y = y;
    }
    
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        isHovered = isMouseOver(mouseX, mouseY);
        
        // Draw button background with custom style
        int color = enabled ? enabledColor : (isHovered ? hoverColor : normalColor);
        
        // Rounded rectangle effect using multiple fills
        context.fill(x, y, x + width, y + height, color);
        
        // Draw border
        context.drawHorizontalLine(x, x + width, y, 0x44FFFFFF);
        context.drawHorizontalLine(x, x + width, y + height - 1, 0x44FFFFFF);
        context.drawVerticalLine(x, y, y + height, 0x44FFFFFF);
        context.drawVerticalLine(x + width - 1, y, y + height, 0x44FFFFFF);
        
        // Draw text centered
        int textWidth = mc.textRenderer.getWidth(name);
        int textX = x + (width - textWidth) / 2;
        int textY = y + (height - 8) / 2;
        
        context.drawTextWithShadow(mc.textRenderer, name, textX, textY, textColor);
        
        // Draw status indicator
        if (enabled) {
            context.fill(x + width - 8, y + 4, x + width - 4, y + 8, 0xFF00FF00);
        }
    }
    
    public boolean isMouseOver(int mouseX, int mouseY) {
        return mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY <= y + height;
    }
    
    public void onClick(int button) {
        if (button == 0) { // Left click
            enabled = !enabled;
            onToggle();
        } else if (button == 1) { // Right click
            // Open settings or context menu
        }
    }
    
    protected void onToggle() {
        // Override this method to handle module toggle
        System.out.println("Module '" + name + "' toggled: " + enabled);
    }
    
    public String getName() {
        return name;
    }
    
    public boolean isEnabled() {
        return enabled;
    }
    
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
    
    public int getX() {
        return x;
    }
    
    public int getY() {
        return y;
    }
    
    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
