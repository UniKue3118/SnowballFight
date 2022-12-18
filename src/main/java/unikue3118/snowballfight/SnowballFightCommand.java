package unikue3118.snowballfight;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class SnowballFightCommand implements CommandExecutor {
    static Boolean SnowballFight = false;
        @Override
        public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
            if(SnowballFight == false) {
                SnowballFight = true;
                sender.sendMessage("ON!");
                return true;
            }
            else if(SnowballFight == true) {
                SnowballFight = false;
                sender.sendMessage("OFF!");
                return true;
            }
            return false;
        }
}
