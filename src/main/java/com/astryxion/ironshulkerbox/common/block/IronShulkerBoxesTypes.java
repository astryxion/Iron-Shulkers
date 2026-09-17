package com.astryxion.ironshulkerbox.common.block;

import com.astryxion.ironshulkerbox.common.Util;
import com.astryxion.ironshulkerbox.common.block.entity.AbstractIronShulkerBoxBlockEntity;
import com.astryxion.ironshulkerbox.common.block.entity.CopperShulkerBoxBlockEntity;
import com.astryxion.ironshulkerbox.common.block.entity.CrystalShulkerBoxBlockEntity;
import com.astryxion.ironshulkerbox.common.block.entity.DiamondShulkerBoxBlockEntity;
import com.astryxion.ironshulkerbox.common.block.entity.GoldShulkerBoxBlockEntity;
import com.astryxion.ironshulkerbox.common.block.entity.IronShulkerBoxBlockEntity;
import com.astryxion.ironshulkerbox.common.block.entity.ObsidianShulkerBoxBlockEntity;
import com.astryxion.ironshulkerbox.common.registraton.IronShulkerBoxesBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.Identifier;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

import org.jetbrains.annotations.Nullable;
import java.util.Locale;

public enum IronShulkerBoxesTypes implements StringRepresentable {
  IRON(54, 9, 184, 222, Identifier.fromNamespaceAndPath("ironshulkerbox", "textures/gui/iron_container.png"), 256, 256),
  GOLD(81, 9, 184, 276, Identifier.fromNamespaceAndPath("ironshulkerbox", "textures/gui/gold_container.png"), 256, 276),
  DIAMOND(108, 12, 238, 276, Identifier.fromNamespaceAndPath("ironshulkerbox", "textures/gui/diamond_container.png"), 256, 276),
  COPPER(45, 9, 184, 204, Identifier.fromNamespaceAndPath("ironshulkerbox", "textures/gui/copper_container.png"), 256, 256),
  CRYSTAL(108, 12, 238, 276, Identifier.fromNamespaceAndPath("ironshulkerbox", "textures/gui/diamond_container.png"), 256, 276),
  OBSIDIAN(108, 12, 238, 276, Identifier.fromNamespaceAndPath("ironshulkerbox", "textures/gui/diamond_container.png"), 256, 276),
  VANILLA(0, 0, 0, 0, Identifier.withDefaultNamespace("textures/gui/container/shulker_box.png"), 0, 0);

  private final String name;
  public final int size;
  public final int rowLength;
  public final int xSize;
  public final int ySize;
  public final Identifier guiTexture;
  public final int textureXSize;
  public final int textureYSize;

  IronShulkerBoxesTypes(int size, int rowLength, int xSize, int ySize, Identifier guiTexture, int textureXSize, int textureYSize) {
    this.name = Util.toEnglishName(this.name());
    this.size = size;
    this.rowLength = rowLength;
    this.xSize = xSize;
    this.ySize = ySize;
    this.guiTexture = guiTexture;
    this.textureXSize = textureXSize;
    this.textureYSize = textureYSize;
  }

  public String getId() {
    return this.name().toLowerCase(Locale.ROOT);
  }

  public String getEnglishName() {
    return this.name;
  }

  @Override
  public String getSerializedName() {
    return this.getEnglishName();
  }

  public int getRowCount() {
    return this.size / this.rowLength;
  }

  public boolean isTransparent() {
    return this == CRYSTAL;
  }

  @Nullable
  public AbstractIronShulkerBoxBlockEntity makeEntity(BlockPos blockPos, BlockState blockState, @Nullable DyeColor color) {
    return switch (this) {
      case IRON -> new IronShulkerBoxBlockEntity(color, blockPos, blockState);
      case GOLD -> new GoldShulkerBoxBlockEntity(color, blockPos, blockState);
      case DIAMOND -> new DiamondShulkerBoxBlockEntity(color, blockPos, blockState);
      case COPPER -> new CopperShulkerBoxBlockEntity(color, blockPos, blockState);
      case CRYSTAL -> new CrystalShulkerBoxBlockEntity(color, blockPos, blockState);
      case OBSIDIAN -> new ObsidianShulkerBoxBlockEntity(color, blockPos, blockState);
      default -> null;
    };
  }

  public static Block get(IronShulkerBoxesTypes type, @Nullable DyeColor color) {
    if (color == null) {
      return switch (type) {
        case IRON -> IronShulkerBoxesBlocks.IRON_SHULKER_BOX;
        case GOLD -> IronShulkerBoxesBlocks.GOLD_SHULKER_BOX;
        case DIAMOND -> IronShulkerBoxesBlocks.DIAMOND_SHULKER_BOX;
        case CRYSTAL -> IronShulkerBoxesBlocks.CRYSTAL_SHULKER_BOX;
        case COPPER -> IronShulkerBoxesBlocks.COPPER_SHULKER_BOX;
        case OBSIDIAN -> IronShulkerBoxesBlocks.OBSIDIAN_SHULKER_BOX;
        default -> Blocks.SHULKER_BOX;
      };
    } else {
      return switch (type) {
        case IRON -> IronShulkerBoxesBlocks.IRON_SHULKER_BOXES.get(color);
        case GOLD -> IronShulkerBoxesBlocks.GOLD_SHULKER_BOXES.get(color);
        case DIAMOND -> IronShulkerBoxesBlocks.DIAMOND_SHULKER_BOXES.get(color);
        case CRYSTAL -> IronShulkerBoxesBlocks.CRYSTAL_SHULKER_BOXES.get(color);
        case COPPER -> IronShulkerBoxesBlocks.COPPER_SHULKER_BOXES.get(color);
        case OBSIDIAN -> IronShulkerBoxesBlocks.OBSIDIAN_SHULKER_BOXES.get(color);
        default -> Blocks.DYED_SHULKER_BOX.pick(color);
      };
    }
  }
}
