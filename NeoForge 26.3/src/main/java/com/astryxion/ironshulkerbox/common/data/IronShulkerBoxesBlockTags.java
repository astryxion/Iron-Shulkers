package com.astryxion.ironshulkerbox.common.data;

import com.astryxion.ironshulkerbox.IronShulkerBoxes;
import com.astryxion.ironshulkerbox.common.registraton.IronShulkerBoxesBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;

import java.util.concurrent.CompletableFuture;

public class IronShulkerBoxesBlockTags extends BlockTagsProvider {

  public IronShulkerBoxesBlockTags(PackOutput output, CompletableFuture<HolderLookup.Provider> lookup) {
    super(output, lookup, IronShulkerBoxes.MODID);
  }

  @Override
  protected void addTags(HolderLookup.Provider provider) {
    var shulkerBoxes = this.tag(BlockTags.SHULKER_BOXES);

    shulkerBoxes.add(
        IronShulkerBoxesBlocks.IRON_SHULKER_BOX.getKey(),
        IronShulkerBoxesBlocks.GOLD_SHULKER_BOX.getKey(),
        IronShulkerBoxesBlocks.DIAMOND_SHULKER_BOX.getKey(),
        IronShulkerBoxesBlocks.COPPER_SHULKER_BOX.getKey(),
        IronShulkerBoxesBlocks.CRYSTAL_SHULKER_BOX.getKey(),
        IronShulkerBoxesBlocks.OBSIDIAN_SHULKER_BOX.getKey());

    IronShulkerBoxesBlocks.IRON_SHULKER_BOXES.forEach((dyeColor, block) -> shulkerBoxes.add(block.getKey()));
    IronShulkerBoxesBlocks.GOLD_SHULKER_BOXES.forEach((dyeColor, block) -> shulkerBoxes.add(block.getKey()));
    IronShulkerBoxesBlocks.DIAMOND_SHULKER_BOXES.forEach((dyeColor, block) -> shulkerBoxes.add(block.getKey()));
    IronShulkerBoxesBlocks.COPPER_SHULKER_BOXES.forEach((dyeColor, block) -> shulkerBoxes.add(block.getKey()));
    IronShulkerBoxesBlocks.CRYSTAL_SHULKER_BOXES.forEach((dyeColor, block) -> shulkerBoxes.add(block.getKey()));
    IronShulkerBoxesBlocks.OBSIDIAN_SHULKER_BOXES.forEach((dyeColor, block) -> shulkerBoxes.add(block.getKey()));
  }
}
