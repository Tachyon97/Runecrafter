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

public class BankStuff extends Task {
    private GameObject object = GameObjects.closest(34748);


    public BankStuff(AbstractScript s) {
        super(s);
    }


    @Override
    public boolean validate() {
        return Bank.isOpen();
    }

    @Override
    public int execute() {
        MethodProvider.log("BankStuffActive");
        if (Inventory.contains("Air rune")) {
            Bank.depositAll("Air rune");
        }
        if (!Inventory.contains("Pure essence")) {
            Bank.withdrawAll("Pure essence");
            MethodProvider.sleepUntil(() -> Inventory.contains("Pure essence"), 1500);
        }
        return 500;
    }
}
