package com.pan233.skyline.events;

import com.google.inject.Inject;
import com.pan233.skyline.NaiceSign;
import com.pan233.skyline.utils.USignInfo;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.block.tileentity.TileEntity;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.block.InteractBlockEvent;
import org.spongepowered.api.event.filter.cause.First;
import org.spongepowered.api.text.Text;

import java.util.Optional;

public class EInteractBlock {

    @Inject
    private NaiceSign plugin;

    @Listener
    public void handle(InteractBlockEvent.Secondary event, @First Player player)
    {
        plugin.getLogger().info("右击了");
        Optional<TileEntity> sign = USignInfo.getSign(event.getTargetBlock());
        if (sign.isPresent()) {
            if (USignInfo.isPermissive(event.getTargetBlock())) {
                String point = USignInfo.getTargetText(sign.get(),4).orElse(Text.of("")).toPlain();
                if (!point.equals("")) {
                    String comm = "tppos "+player.getName()+" "+point.substring(1,point.length()-1).replace(","," ");
                    Sponge.getCommandManager().process(Sponge.getServer().getConsole(),comm);
                } else {
                    player.sendMessage(Text.of("传送位置损坏！"));
                }
            } else {
                player.sendMessage(Text.of("需要龙蛋注入魔力！"));
            }
        }
    }
}
