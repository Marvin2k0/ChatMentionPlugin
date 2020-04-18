package de.marvin2k0.chatmention;

import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class ChatMention extends JavaPlugin implements Listener
{
    @Override
    public void onEnable()
    {
        this.getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event)
    {
        String[] arr = event.getMessage().split(" ");
        String playerName = "";

        for (String str : arr)
        {
            if (str.startsWith("@"))
                playerName = str.replaceFirst("@", "");
        }

        if (!playerName.isEmpty())
        {
            event.setCancelled(true);

            Player player = Bukkit.getPlayer(playerName);

            for (Player p : Bukkit.getOnlinePlayers())
            {
                if (player != null && player.getUniqueId().equals(p.getUniqueId()))
                {
                    player.sendMessage("<" + event.getPlayer().getDisplayName() + "> " + event.getMessage().replace("@" + player.getName(), "§9@" + player.getName() + "§f"));
                }
                else
                {
                    p.sendMessage("<" + event.getPlayer().getDisplayName() + "> " + event.getMessage());
                }
            }
        }
    }
}
