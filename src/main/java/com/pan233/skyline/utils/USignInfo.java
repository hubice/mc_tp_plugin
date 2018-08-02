package com.pan233.skyline.utils;

import org.spongepowered.api.block.BlockSnapshot;
import org.spongepowered.api.block.BlockTypes;
import org.spongepowered.api.block.tileentity.TileEntity;
import org.spongepowered.api.data.manipulator.mutable.tileentity.SignData;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.world.Location;
import org.spongepowered.api.world.World;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class USignInfo {

       // 通过地址获取方块
        public static Optional<TileEntity> getSign(BlockSnapshot blockSnapshot) {
            if (blockSnapshot.getState().getType() == BlockTypes.WALL_SIGN) {
                Optional<Location<World>> _loc = blockSnapshot.getLocation();
                if (_loc.isPresent()) {
                    return _loc.get().getTileEntity();
                }
            }
            return Optional.empty();
        }

        // 获取字体
        public static Optional<Text> getTargetText(TileEntity Sign, int line) {
            Optional<SignData> data = Sign.getOrCreate(SignData.class);
            if (data.isPresent()) {
                Text text = data.get().lines().get(line - 1);
                return Optional.of(text);
            }
            return Optional.empty();
        }

        // 获取name
        public static boolean isPermissive(BlockSnapshot blockSnapshot) {
            Optional<Location<World>> _loc = blockSnapshot.getLocation();
            if (_loc.isPresent()) {
                 List<Location<World>> _rand = new ArrayList<Location<World>>();
                _rand.add(_loc.get().sub(-1,1,0));
                _rand.add(_loc.get().sub(0,1,-1));
                _rand.add(_loc.get().sub(1,1,0));
                _rand.add(_loc.get().sub(0,1,1));
                for (Location<World> i : _rand
                     ) {
                    if (i.getBlockType() == BlockTypes.DRAGON_EGG) {
                        return  true;
                    }
                }
            }
            return false;
        }
}
