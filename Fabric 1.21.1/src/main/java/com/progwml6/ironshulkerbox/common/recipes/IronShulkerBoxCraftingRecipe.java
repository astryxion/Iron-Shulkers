package com.progwml6.ironshulkerbox.common.recipes;

import com.mojang.serialization.MapCodec;
import com.progwml6.ironshulkerbox.common.block.AbstractIronShulkerBoxBlock;
import com.progwml6.ironshulkerbox.common.registraton.IronShulkerBoxesRecipes;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.CraftingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.ShapedRecipe;
import net.minecraft.world.level.Level;
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
  public ItemStack assemble(CraftingInput input, HolderLookup.Provider registries) {
    ItemStack result = this.inner.assemble(input, registries);
    for (int slot = 0; slot < input.size(); slot++) {
      ItemStack stack = input.getItem(slot);
      if (isShulkerBox(stack)) {
        return stack.transmuteCopy(result.getItem(), result.getCount());
      }
    }
    return result;
  }

  @Override
  public boolean canCraftInDimensions(int width, int height) {
    return this.inner.canCraftInDimensions(width, height);
  }

  @Override
  public ItemStack getResultItem(HolderLookup.Provider registries) {
    return this.inner.getResultItem(registries);
  }

  @Override
  public NonNullList<Ingredient> getIngredients() {
    return this.inner.getIngredients();
  }

  @Override
  public String getGroup() {
    return this.inner.getGroup();
  }

  @Override
  public CraftingBookCategory category() {
    return this.inner.category();
  }

  @Override
  public boolean showNotification() {
    return this.inner.showNotification();
  }

  @Override
  public RecipeSerializer<?> getSerializer() {
    return IronShulkerBoxesRecipes.SHULKER_BOX_CRAFTING;
  }

  private static boolean isShulkerBox(ItemStack stack) {
    if (stack.isEmpty()) {
      return false;
    }
    Block block = Block.byItem(stack.getItem());
    return block instanceof ShulkerBoxBlock || block instanceof AbstractIronShulkerBoxBlock;
  }

  public static class Serializer implements RecipeSerializer<IronShulkerBoxCraftingRecipe> {

    public static final MapCodec<IronShulkerBoxCraftingRecipe> CODEC =
      RecipeSerializer.SHAPED_RECIPE.codec().xmap(IronShulkerBoxCraftingRecipe::new, IronShulkerBoxCraftingRecipe::inner);

    public static final StreamCodec<RegistryFriendlyByteBuf, IronShulkerBoxCraftingRecipe> STREAM_CODEC =
      RecipeSerializer.SHAPED_RECIPE.streamCodec().map(IronShulkerBoxCraftingRecipe::new, IronShulkerBoxCraftingRecipe::inner);

    @Override
    public MapCodec<IronShulkerBoxCraftingRecipe> codec() {
      return CODEC;
    }

    @Override
    public StreamCodec<RegistryFriendlyByteBuf, IronShulkerBoxCraftingRecipe> streamCodec() {
      return STREAM_CODEC;
    }
  }
}
