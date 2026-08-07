# VisualClient - Custom Click GUI Mod for Minecraft 1.21.4 Fabric

## Features
- **Custom Click GUI** with modern, non-vanilla design
- Opens with **Right Shift** key
- Beautiful gradient backgrounds and custom colors
- Module categories: Combat, Render, Movement, Player, World
- Toggle modules with left-click
- Hover effects and visual feedback

## Installation
1. Install [Fabric Loader](https://fabricmc.net/use/) for Minecraft 1.21.4
2. Install [Fabric API](https://modrinth.com/mod/fabric-api)
3. Build the mod using `gradlew build`
4. Copy the jar from `build/libs/` to your mods folder

## Building
```bash
./gradlew build
```

## Usage
- Press **Right Shift** in-game to open the Click GUI
- Click on module categories to toggle them
- Press **Right Shift** or **ESC** to close the GUI

## Customization
You can customize the GUI colors in `ClickGui.java`:
- `backgroundColor` - Main background color
- `accentColor` - Border and highlight color
- `textColor` - Text color
- `buttonBackgroundColor` - Button background color

## Project Structure
```
src/main/java/com/visualclient/
├── VisualClient.java          # Main mod initializer
├── VisualClientClient.java    # Client-side initializer with key binding
├── gui/
│   ├── ClickGui.java          # Main GUI screen
│   └── ModuleButton.java      # Module button component
└── mixin/
    └── KeyboardMixin.java     # Keyboard event mixin

src/main/resources/
├── fabric.mod.json            # Mod metadata
├── visualclient.mixins.json   # Mixin configuration
└── assets/visualclient/       # Assets (lang, icons, etc.)
```

## License
MIT License
