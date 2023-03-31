package com.tasks;

import com.endpass.Task;
import org.dreambot.api.methods.MethodProvider;
import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.container.impl.bank.Bank;
import org.dreambot.api.methods.container.impl.bank.BankLocation;
import org.dreambot.api.methods.interactive.GameObjects;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.script.AbstractScript;
import org.dreambot.api.wrappers.interactive.GameObject;

public class BankingMode extends Task {

    public BankingMode(AbstractScript s) {
        super(s);
    }


    @Override
    public boolean validate() {
        return Inventory.contains("Air rune") && !Bank.isOpen() && !Players.localPlayer().isMoving();
    }

    @Override
    public int execute() {
        MethodProvider.log("BankingMode active");
        Bank.open(BankLocation.FALADOR_EAST);
        return 500;
    }
}
