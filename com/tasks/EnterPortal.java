package com.tasks;

import com.endpass.Task;
import org.dreambot.api.methods.MethodProvider;
import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.interactive.GameObjects;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.map.Tile;
import org.dreambot.api.methods.walking.impl.Walking;
import org.dreambot.api.script.AbstractScript;
import org.dreambot.api.wrappers.interactive.GameObject;

import java.lang.reflect.Method;

public class EnterPortal extends Task {

    private Tile point = new Tile(2841, 4830, 0);
    private GameObject object;
    private Area altarArea = new Area(2982, 3295, 2990, 3287);


    public EnterPortal(AbstractScript s) {
        super(s);
    }

    @Override
    public boolean validate() {
        return Inventory.contains("Pure Essence") && altarArea.contains(Players.localPlayer());
    }

    @Override
    public int execute() {
        object = GameObjects.closest("Mysterious ruins");
        MethodProvider.log("This is active now: EnterPortal");
        if (object != null && object.isOnScreen() && object.exists()) {
            if (object.interact("Enter")) {
                MethodProvider.sleepUntil(() -> Players.localPlayer().getTile().distance(point) <= 2, 1500);
                MethodProvider.log("Just interacted Portal");

            }
        }
        return 500;
    }
}
