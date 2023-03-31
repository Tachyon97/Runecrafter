package com.tasks;

import com.endpass.Task;
import org.dreambot.api.methods.MethodProvider;
import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.map.Tile;
import org.dreambot.api.methods.walking.impl.Walking;
import org.dreambot.api.script.AbstractScript;

public class WalkToPortal extends Task {

    private Area altarArea = new Area(2982, 3295, 2990, 3287);
    private Tile point = new Tile(2841, 4830, 0);

    public WalkToPortal(AbstractScript s) {
        super(s);
    }

    @Override
    public boolean validate() {
        return Inventory.contains("Pure Essence") &&
                !altarArea.contains(Players.localPlayer()) &&
                !Players.localPlayer().isMoving()
                && !Players.localPlayer().getTile().equals(point);
    }

    @Override
    public int execute() {
        MethodProvider.log("This is active: WalkToPortal");
        Walking.walk(altarArea.getRandomTile());
        return 500;
    }
}
