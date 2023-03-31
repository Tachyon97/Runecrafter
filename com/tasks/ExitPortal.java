package com.tasks;

import com.endpass.Task;
import org.dreambot.api.methods.MethodProvider;
import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.interactive.GameObjects;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.map.Tile;
import org.dreambot.api.script.AbstractScript;
import org.dreambot.api.wrappers.interactive.GameObject;

public class ExitPortal extends Task {

    private Tile point = new Tile(2989, 3295, 0);
    private GameObject object;

    public ExitPortal(AbstractScript s) {
        super(s);
    }

    @Override
    public boolean validate() {
        return Inventory.contains("Air rune") ;
    }

    @Override
    public int execute() {
        object = GameObjects.closest(34748);
        if (object != null && object.isOnScreen() && object.exists()) {
            if (object.interact()) {
                MethodProvider.sleepUntil(() -> !object.isOnScreen(), 1500);
            }
        }
        MethodProvider.log("Exitportal Active");

        return 500;
    }
}
