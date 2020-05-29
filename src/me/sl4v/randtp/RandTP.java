package me.sl4v.randtp;

import org.bukkit.plugin.java.JavaPlugin;

public class RandTP extends JavaPlugin
{
    @Override
    public void onEnable()
    {
        getLogger().info("RandTP Enabled.");
        getCommand("randtp").setExecutor(new RandTPCommand());
    }
}
