package com.endpass;

import java.util.ArrayList;

public class TaskHandler {
    private static ArrayList<Task> tasks = new ArrayList<>();

    public static Task[] tasks() {
        return tasks.toArray(new Task[tasks.size()]);
    }

    public static void addTask(Task t) {
        tasks.add(t);
    }

    public static int run() {
        for (Task t : tasks) {
            if (t != null && t.validate()) {
                t.execute();
            }
        }
        return 250;
    }

    public static String addedNodes() {
        String s = "";
        for (Task t : tasks)
            s = t.getClass().getSimpleName() + " Added";
        return s;
    }
}
