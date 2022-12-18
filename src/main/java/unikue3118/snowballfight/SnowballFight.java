package unikue3118.snowballfight;

import org.bukkit.command.CommandExecutor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
public final class SnowballFight extends JavaPlugin implements CommandExecutor, Listener {
    @Override
    public void onEnable() {
        getLogger().info("On!");  //서버의 로그에 출력
        getCommand("snowballfight").setExecutor(new SnowballFightCommand());
        getServer().getPluginManager().registerEvents(new SnowballFightListener(), this);
    }
    @Override
    public void onDisable() {
        getLogger().info("Off."); //서버의 로그에 출력
    }
}
