package de.janlucaklees.broadcraft.main;

import de.janlucaklees.broadcraft.util.Broadcast;
import org.bukkit.plugin.java.JavaPlugin;

public class Broadcraft extends JavaPlugin {
    private Broadcast broadcast;

    @Override
    public void onEnable() {
        this.broadcast = new Broadcast(this, "Hello", 0, 5*20);
        this.broadcast.start();
    }

    @Override
    public void onDisable() {
        this.broadcast.stop();
        this.broadcast = null;
    }
}
