package com.astryxion.ironshulkerbox.common.data;

import com.astryxion.ironshulkerbox.common.registraton.IronShulkerBoxesBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;

import java.util.concurrent.CompletableFuture;

public class IronShulkerBoxesBlockTags extends FabricTagsProvider.BlockTagsProvider {

  public IronShulkerBoxesBlockTags(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookupFuture) {
    super(output, registryLookupFuture);
  }

  @Override
  protected void addTags(HolderLookup.Provider provider) {
    var shulkerBoxes = tag(BlockTags.SHULKER_BOXES);

    shulkerBoxes.add(
        key(IronShulkerBoxesBlocks.IRON_SHULKER_BOX),
        key(IronShulkerBoxesBlocks.GOLD_SHULKER_BOX),
        key(IronShulkerBoxesBlocks.DIAMOND_SHULKER_BOX),
        key(IronShulkerBoxesBlocks.COPPER_SHULKER_BOX),
        key(IronShulkerBoxesBlocks.CRYSTAL_SHULKER_BOX),
        key(IronShulkerBoxesBlocks.OBSIDIAN_SHULKER_BOX));

    IronShulkerBoxesBlocks.IRON_SHULKER_BOXES.forEach((dyeColor, block) -> shulkerBoxes.add(key(block)));
    IronShulkerBoxesBlocks.GOLD_SHULKER_BOXES.forEach((dyeColor, block) -> shulkerBoxes.add(key(block)));
    IronShulkerBoxesBlocks.DIAMOND_SHULKER_BOXES.forEach((dyeColor, block) -> shulkerBoxes.add(key(block)));
    IronShulkerBoxesBlocks.COPPER_SHULKER_BOXES.forEach((dyeColor, block) -> shulkerBoxes.add(key(block)));
    IronShulkerBoxesBlocks.CRYSTAL_SHULKER_BOXES.forEach((dyeColor, block) -> shulkerBoxes.add(key(block)));
    IronShulkerBoxesBlocks.OBSIDIAN_SHULKER_BOXES.forEach((dyeColor, block) -> shulkerBoxes.add(key(block)));
  }

  private static ResourceKey<Block> key(Block block) {
    return BuiltInRegistries.BLOCK.getResourceKey(block).orElseThrow();
  }
}
