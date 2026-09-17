package com.astryxion.ironshulkerbox.common.recipes;

import com.mojang.serialization.MapCodec;
import com.astryxion.ironshulkerbox.common.block.AbstractIronShulkerBoxBlock;
import com.astryxion.ironshulkerbox.common.registraton.IronShulkerBoxesRecipes;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.CraftingRecipe;
import net.minecraft.world.item.crafting.PlacementInfo;
import net.minecraft.world.item.crafting.RecipeBookCategory;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.ShapedRecipe;
import net.minecraft.world.item.crafting.display.RecipeDisplay;
import net.minecraft.world.level.Level;

import java.util.List;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ShulkerBoxBlock;

public class IronShulkerBoxCraftingRecipe implements CraftingRecipe {

  private final ShapedRecipe inner;

  public IronShulkerBoxCraftingRecipe(ShapedRecipe inner) {
    this.inner = inner;
  }

  public ShapedRecipe inner() {
    return this.inner;
  }

  @Override
  public boolean matches(CraftingInput input, Level level) {
    return this.inner.matches(input, level);
  }

  @Override
  public ItemStack assemble(CraftingInput input) {
    ItemStack result = this.inner.assemble(input);
    for (int slot = 0; slot < input.size(); slot++) {
      ItemStack stack = input.getItem(slot);
      if (isShulkerBox(stack)) {
        return stack.transmuteCopy(result.getItem(), result.getCount());
      }
    }
    return result;
  }

  @Override
  public CraftingBookCategory category() {
    return this.inner.category();
  }

  @Override
  public String group() {
    return this.inner.group();
  }

  @Override
  public boolean showNotification() {
    return this.inner.showNotification();
  }

  @Override
  public PlacementInfo placementInfo() {
    return this.inner.placementInfo();
  }

  @Override
  public RecipeBookCategory recipeBookCategory() {
    return this.inner.recipeBookCategory();
  }

  @Override
  public List<RecipeDisplay> display() {
    return this.inner.display();
  }

  @Override
  public RecipeSerializer<? extends CraftingRecipe> getSerializer() {
    return IronShulkerBoxesRecipes.SHULKER_BOX_CRAFTING.get();
  }

  private static boolean isShulkerBox(ItemStack stack) {
    if (stack.isEmpty()) {
      return false;
    }
    Block block = Block.byItem(stack.getItem());
    return block instanceof ShulkerBoxBlock || block instanceof AbstractIronShulkerBoxBlock;
  }

  public static final MapCodec<IronShulkerBoxCraftingRecipe> CODEC =
    ShapedRecipe.SERIALIZER.codec().xmap(IronShulkerBoxCraftingRecipe::new, IronShulkerBoxCraftingRecipe::inner);

  public static final StreamCodec<RegistryFriendlyByteBuf, IronShulkerBoxCraftingRecipe> STREAM_CODEC =
    ShapedRecipe.SERIALIZER.streamCodec().map(IronShulkerBoxCraftingRecipe::new, IronShulkerBoxCraftingRecipe::inner);
}
