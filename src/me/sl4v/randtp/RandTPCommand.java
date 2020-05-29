package me.sl4v.randtp;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import java.util.Random;

public class RandTPCommand implements CommandExecutor
{
    private Random rand = new Random();

    private void teleportPlayer(Player player)
    {
        World playerWorld = player.getWorld();
        int low = -29999984;
        int high = 29999984;
        double X = (double) rand.nextInt(high - low) + low;
        double Z = (double) rand.nextInt(high - low) + low;
        double Y = playerWorld.getHighestBlockYAt((int) X, (int) Z);
        Location newLocation = new Location(playerWorld, X, Y, Z);
        Material blockType = newLocation.getBlock().getType();
        if (blockType == Material.WATER)
        {
            teleportPlayer(player);
            return;
        }
        newLocation.getChunk().load();
        player.sendMessage(String.format("%sTeleporting to %s %s %s %s", ChatColor.AQUA, ChatColor.RED, X, Y, Z));
        player.teleport(newLocation);
        player.sendMessage(ChatColor.AQUA + "Teleported.");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        if (sender instanceof Player && command.getName().toLowerCase().equals("randtp"))
        {
            Player player = (Player) sender;
            teleportPlayer(player);
        }
        return true;
    }
}
