package unikue3118.snowballfight;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.ItemStack;

public class SnowballFightListener implements Listener {
    @EventHandler
    public void onProjectileHit(ProjectileHitEvent snow) { //투사체 착탄시 실행
        if (SnowballFightCommand.SnowballFight == true & snow.getEntity().getShooter() instanceof Player) {  //만약 플레이어가 눈덩이를 쏘면
            Player shooter = (Player) snow.getEntity().getShooter();

            if(shooter.getGameMode() == GameMode.SURVIVAL|shooter.getGameMode() == GameMode.ADVENTURE){
                ItemStack snowball = new ItemStack(Material.SNOWBALL);
                int shocount = 0;
                for (int i = 0; i < 36; i++) {
                    ItemStack slot = shooter.getInventory().getItem(i);
                    if (slot == null || !slot.isSimilar(snowball))
                        continue;
                    shocount += slot.getAmount();
                }
                if(shocount >= 16) {
                    snowball.setAmount(32);
                    shooter.getInventory().removeItemAnySlot(snowball);
                    snowball.setAmount(16);
                    shooter.getInventory().addItem(snowball);
                }
                else {
                    snowball.setAmount(1);
                    shooter.getInventory().addItem(snowball);
                }
            }
        }
    }
    @EventHandler
    public void onDeath(PlayerDeathEvent snowKill) {
        if (SnowballFightCommand.SnowballFight == true & snowKill.getEntity().getKiller() instanceof Player) {
            Player shooter = (Player) snowKill.getEntity().getKiller();
            Player victim = (Player) snowKill.getEntity();
            if(shooter.getGameMode() == GameMode.SURVIVAL|shooter.getGameMode() == GameMode.ADVENTURE){
                ItemStack snowball = new ItemStack(Material.SNOWBALL);
                int shocount = 0;
                for (int i = 0; i < 36; i++) {
                    ItemStack slot = shooter.getInventory().getItem(i);
                    if (slot == null || !slot.isSimilar(snowball))
                        continue;
                    shocount += slot.getAmount();
                }
                int viccount = 0;
                for (int i = 0; i < 36; i++) {
                    ItemStack slot = victim.getInventory().getItem(i);
                    if (slot == null || !slot.isSimilar(snowball))
                        continue;
                    viccount += slot.getAmount();
                }
                Integer vicchange = Integer.valueOf(viccount / 2);
                Integer shochange = Integer.valueOf(vicchange / 2) + 1;
                if(shocount < 16) {
                    snowball.setAmount(shochange);
                    shooter.getInventory().addItem(snowball);
                }
                if(shocount+shochange > 16) {
                    snowball.setAmount(32);
                    shooter.getInventory().removeItemAnySlot(snowball);
                    snowball.setAmount(15);
                    shooter.getInventory().addItem(snowball);
                }
                shochange = 0;
                if(viccount <= 1){
                    vicchange = 0;
                    return;
                }
                else {
                    snowball.setAmount(vicchange);
                    victim.getInventory().removeItemAnySlot(snowball);
                    vicchange = 0;
                }
            }
        }
    }
}
