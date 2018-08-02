package com.pan233.skyline;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.pan233.skyline.events.EInteractBlock;
import org.slf4j.Logger;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.GameInitializationEvent;
import org.spongepowered.api.event.game.state.GamePostInitializationEvent;
import org.spongepowered.api.event.game.state.GamePreInitializationEvent;
import org.spongepowered.api.event.game.state.GameStartedServerEvent;
import org.spongepowered.api.plugin.Plugin;

/**
 * 1.需要配合lore使用,不会有权限控制的。
 * 2.需要config吗(不)
 */
@Plugin(id = NaiceSign.ID, name = "naice sign", version = "0.1", description = "An sign sponge plugin")
public class NaiceSign {

    public static final String ID = "naicesign";

    @Inject
    private Logger logger;
    public Logger getLogger(){return logger;}

    @Inject
    private Injector injector;

    @Listener
    public void preinit(GamePreInitializationEvent event) {
        logger.info("牌子插件初始化！");
    }

    @Listener
    public void init(GameInitializationEvent event) {
        EInteractBlock eve = injector.getInstance(EInteractBlock.class);
        Sponge.getEventManager().registerListeners(this,eve);
    }

    @Listener
    public void postinit(GamePostInitializationEvent event) {

    }

    @Listener
    public void start(GameStartedServerEvent event) {

    }
}
