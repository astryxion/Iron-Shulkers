package com.astryxion.ironshulkerbox.common.block;

import com.astryxion.ironshulkerbox.common.block.entity.GoldShulkerBoxBlockEntity;
import com.astryxion.ironshulkerbox.common.registraton.IronShulkerBoxesBlockEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class GoldShulkerBoxBlock extends AbstractIronShulkerBoxBlock {

  public GoldShulkerBoxBlock(Properties properties, @Nullable DyeColor color) {
    super(properties, color, () -> IronShulkerBoxesBlockEntityTypes.GOLD_SHULKER_BOX, IronShulkerBoxesTypes.GOLD);
  }

  @Override
  public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
    return new GoldShulkerBoxBlockEntity(this.color, pPos, pState);
  }

}
