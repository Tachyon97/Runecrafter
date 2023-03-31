package com.endpass;

import org.dreambot.api.script.AbstractScript;

public abstract class Task {
    protected AbstractScript s;

    public Task(AbstractScript s) {
        this.s = s;
    }

    public abstract boolean validate();

    public abstract int execute();
}
