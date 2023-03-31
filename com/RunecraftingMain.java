package com;

import com.endpass.Task;
import com.endpass.TaskHandler;
import com.tasks.*;
import org.dreambot.api.input.Mouse;
import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.skills.Skill;
import org.dreambot.api.methods.skills.SkillTracker;
import org.dreambot.api.script.AbstractScript;
import org.dreambot.api.script.Category;
import org.dreambot.api.script.ScriptManifest;
import org.dreambot.api.utilities.Timer;

import java.awt.*;

@ScriptManifest(category = Category.RUNECRAFTING, name = "Code[ Runecrafter", author = "Calle", version = 1.1)
public class RunecraftingMain extends AbstractScript {


    private Timer runTime = new Timer();
    private String type;
    private FontMetrics fontMetrics;


    @Override
    public void onPaint(Graphics g) {
        drawText(g, "Code[ Runecrafting 1.0", 10, 240, Color.yellow);
        drawText(g, "Start Level: " + SkillTracker.getStartLevel(Skill.RUNECRAFTING) + " (+" + SkillTracker.getGainedLevels(Skill.RUNECRAFTING) + ")", 10, 262, Color.yellow);
        drawText(g, "Gained Xp: " + SkillTracker.getGainedExperience(Skill.RUNECRAFTING) + " (" + SkillTracker.getGainedExperiencePerHour(Skill.RUNECRAFTING) + ")", 10, 284, Color.yellow);

        drawText(g, "Elapsed Time: " + Timer.formatTime(runTime.elapsed()), 10, 306, Color.yellow);
        //drawText(g, "Caught: " + (SkillTracker.getGainedExperience(Skill.RUNECRAFTING)/110) + " (" + (SkillTracker.getGainedExperiencePerHour(Skill.RUNECRAFTING)/110) + ")", 10, 328, Color.yellow);

        g.drawString("Type: Shark. Mode: Banking", 10, 368);
    }

    @Override
    public int onLoop() {
        // simpleAntiban();
        return TaskHandler.run();
    }

    private void simpleAntiban() {
        if (!Inventory.isFull() && Players.localPlayer().isAnimating())
            Mouse.moveMouseOutsideScreen();
    }

    private void loadedTasks() {
        //   for(Task t: Task)
    }

    @Override
    public void onStart() {
        SkillTracker.start();
        TaskHandler.addTask(new BankingMode(this));
        TaskHandler.addTask(new BankStuff(this));
        TaskHandler.addTask(new EnterPortal(this));
        TaskHandler.addTask(new ExitPortal(this));
        TaskHandler.addTask(new WalkToPortal(this));
        TaskHandler.addTask(new CraftRunes(this));
        TaskHandler.addedNodes();

    }

    public void drawText(Graphics g, String text, int x, int y, Color c) {
        Graphics2D g2 = (Graphics2D) g;
        Color color3 = new Color(0, 0, 0, 0);
        Font font1 = new Font("Arial", 1, 12);
        g.setFont(font1);
        fontMetrics = g.getFontMetrics();
        Rectangle textBox = new Rectangle(x, y - g.getFont().getSize(),
                (int) fontMetrics.getStringBounds(text, g).getWidth() + 8,
                (int) fontMetrics.getStringBounds(text, g).getHeight() + 6);
        Paint defaultPaint = g2.getPaint();
        g2.setPaint(new RadialGradientPaint(
                new Point.Double(textBox.x + textBox.width / 2.0D, textBox.y + textBox.height / 2.0D),
                (float) (textBox.getWidth() / 2.0D), new float[]{0.5F, 1.0F},
                new Color[]{new Color(color3.getRed(), color3.getGreen(), color3.getBlue(), 255),
                        new Color(0.0F, 0.0F, 0.0F, 0.8F)}));
        g.fillRect(textBox.x, textBox.y + 12, textBox.width, textBox.height);
        g2.setPaint((Paint) defaultPaint);
        g.setColor(Color.BLACK);
        g.drawRect(textBox.x, textBox.y + 12, textBox.width, textBox.height);
        g.setColor(c);
        g.drawString(text, x + 4, y + 15);
        for (int i = 0; i < text.length(); i++) {
            if (Character.isDigit(text.charAt(i))) {
                g.setColor(new Color(255, 255, 255));
                g.drawString("" + text.charAt(i), x + fontMetrics.stringWidth(text.substring(0, i)) + 4, y + 15);
            }
        }
    }

}
