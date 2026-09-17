package com.progwml6.ironshulkerbox.common.registraton;

import com.progwml6.ironshulkerbox.IronShulkerBoxes;
import com.progwml6.ironshulkerbox.common.block.entity.CopperShulkerBoxBlockEntity;
import com.progwml6.ironshulkerbox.common.block.entity.CrystalShulkerBoxBlockEntity;
import com.progwml6.ironshulkerbox.common.block.entity.DiamondShulkerBoxBlockEntity;
import com.progwml6.ironshulkerbox.common.block.entity.GoldShulkerBoxBlockEntity;
import com.progwml6.ironshulkerbox.common.block.entity.IronShulkerBoxBlockEntity;
import com.progwml6.ironshulkerbox.common.block.entity.ObsidianShulkerBoxBlockEntity;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class IronShulkerBoxesBlockEntityTypes {

  public static BlockEntityType<IronShulkerBoxBlockEntity> IRON_SHULKER_BOX;
  public static BlockEntityType<GoldShulkerBoxBlockEntity> GOLD_SHULKER_BOX;
  public static BlockEntityType<DiamondShulkerBoxBlockEntity> DIAMOND_SHULKER_BOX;
  public static BlockEntityType<CopperShulkerBoxBlockEntity> COPPER_SHULKER_BOX;
  public static BlockEntityType<CrystalShulkerBoxBlockEntity> CRYSTAL_SHULKER_BOX;
  public static BlockEntityType<ObsidianShulkerBoxBlockEntity> OBSIDIAN_SHULKER_BOX;

  public static void register() {
    IRON_SHULKER_BOX = register("iron_shulker_box", IronShulkerBoxBlockEntity::new, IronShulkerBoxBlockEntity.blocksForType());
    GOLD_SHULKER_BOX = register("gold_shulker_box", GoldShulkerBoxBlockEntity::new, GoldShulkerBoxBlockEntity.blocksForType());
    DIAMOND_SHULKER_BOX = register("diamond_shulker_box", DiamondShulkerBoxBlockEntity::new, DiamondShulkerBoxBlockEntity.blocksForType());
    COPPER_SHULKER_BOX = register("copper_shulker_box", CopperShulkerBoxBlockEntity::new, CopperShulkerBoxBlockEntity.blocksForType());
    CRYSTAL_SHULKER_BOX = register("crystal_shulker_box", CrystalShulkerBoxBlockEntity::new, CrystalShulkerBoxBlockEntity.blocksForType());
    OBSIDIAN_SHULKER_BOX = register("obsidian_shulker_box", ObsidianShulkerBoxBlockEntity::new, ObsidianShulkerBoxBlockEntity.blocksForType());
  }

  private static <T extends BlockEntity> BlockEntityType<T> register(String name, BlockEntityType.BlockEntitySupplier<T> entity, Block[] blocks) {
    return Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, IronShulkerBoxes.id(name), BlockEntityType.Builder.of(entity, blocks).build(null));
  }
}
