package me.infinityz;

import com.sk89q.worldedit.CuboidClipboard;
import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.Vector;
import com.sk89q.worldedit.bukkit.BukkitWorld;
import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import com.sk89q.worldedit.schematic.MCEditSchematicFormat;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class NoobstersArenaMatch extends JavaPlugin {

    @Override
    public void onEnable() {
        getCommand("commander").setExecutor(new ex());

    }

    class ex implements CommandExecutor{

        @Override
        public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
            Player p = (Player) commandSender;
            loadSchem(p);
            return true;
        }
    }

    public void loadSchem(Player player){
        Location loc = player.getLocation();
        WorldEditPlugin worldEditPlugin = (WorldEditPlugin) Bukkit.getPluginManager().getPlugin("WorldEdit");
        File schem = new File(getDataFolder() + File.separator +  "/schematics/arena.schematic");
        EditSession session = worldEditPlugin.getWorldEdit().getEditSessionFactory().getEditSession(new BukkitWorld(loc.getWorld()), 100000000);

        for (int i = 0; i < 20; i++) {


            try {
                CuboidClipboard clipboard = MCEditSchematicFormat.getFormat(schem).load(schem);
                int x_off = (int) (loc.getX() + (i *20));

                int z_off = (int) (loc.getZ() + (i *20));
                clipboard.paste(session, new Vector(x_off, loc.getY(), z_off), false);
                Location spawn = new Location(loc.getWorld(),x_off + 2, loc.getY() + 2.5,  z_off + 5.5);
                Bukkit.broadcastMessage("Location " + i + " " + spawn.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
