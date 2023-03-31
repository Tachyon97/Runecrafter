package com.tasks;

import com.endpass.Task;
import org.dreambot.api.methods.MethodProvider;
import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.interactive.GameObjects;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.map.Tile;
import org.dreambot.api.script.AbstractScript;
import org.dreambot.api.wrappers.interactive.GameObject;

public class CraftRunes extends Task {

    private Tile point = new Tile(2841, 4830, 0);
    private GameObject object;

    public CraftRunes(AbstractScript s) {
        super(s);
    }

    @Override
    public boolean validate() {
        return !Inventory.contains("Air rune") && Inventory.contains("Pure Essence")
                && Players.localPlayer().getTile().distance(point) <= 2;
    }

    @Override
    public int execute() {
        object = GameObjects.closest(34760);
        if (object != null) {
            if (GameObjects.closest("Altar").interact()) {
                MethodProvider.sleepUntil(() -> Inventory.contains("Air Rune"), 1500);
                MethodProvider.log("Just interacted altar");
            }
        }
        MethodProvider.log("CraftRunes Active");
        return 500;
    }
}
