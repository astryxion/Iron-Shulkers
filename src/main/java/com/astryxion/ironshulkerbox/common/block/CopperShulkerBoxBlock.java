package com.astryxion.ironshulkerbox.common.block;

import com.astryxion.ironshulkerbox.common.block.entity.CopperShulkerBoxBlockEntity;
import com.astryxion.ironshulkerbox.common.registraton.IronShulkerBoxesBlockEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class CopperShulkerBoxBlock extends AbstractIronShulkerBoxBlock {

  public CopperShulkerBoxBlock(Properties properties, @Nullable DyeColor color) {
    super(properties, color, () -> IronShulkerBoxesBlockEntityTypes.COPPER_SHULKER_BOX, IronShulkerBoxesTypes.COPPER);
  }

  @Override
  public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
    return new CopperShulkerBoxBlockEntity(this.color, pPos, pState);
  }

}
