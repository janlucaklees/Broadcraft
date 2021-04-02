package de.janlucaklees.broadcraft.main;

import de.janlucaklees.broadcraft.util.Broadcast;
import org.bukkit.plugin.java.JavaPlugin;

public class Broadcraft extends JavaPlugin {
    @Override
    public void onEnable() {
        (new Broadcast(this, "Hello", 0, 5*20)).start();
    }
}
