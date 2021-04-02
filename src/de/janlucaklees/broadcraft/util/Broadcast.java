package de.janlucaklees.broadcraft.util;

import de.janlucaklees.broadcraft.error.BroadcastNotStoppedError;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

public class Broadcast {
    private final BukkitScheduler scheduler;

    private final JavaPlugin plugin;

    private final String message;
    private final long delay;
    private final long interval;

    private int task;

    public Broadcast(JavaPlugin plugin, String message, long delay, long interval) {
        this.scheduler = Bukkit.getScheduler();

        this.plugin = plugin;

        this.message  = message;
        this.delay    = delay;
        this.interval = interval;
    }

    private Runnable getRunnable(String message) {
        return new Runnable() {
            @Override
            public void run() {
                Bukkit.getServer().broadcastMessage(message);
            }
        };
    }

    public void start() {
        this.task = this.scheduler.scheduleSyncRepeatingTask(this.plugin, this.getRunnable(this.message), this.delay, this.interval);
    }

    public void stop() {
        this.scheduler.cancelTask(this.task);
    }

    @Override
    protected void finalize() throws Throwable {
        if(this.scheduler.isCurrentlyRunning(this.task)) {
            this.stop();
            throw new BroadcastNotStoppedError();
        }
    }
}
