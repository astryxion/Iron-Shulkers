package com.progwml6.ironshulkerbox.common.recipes;

import com.google.gson.JsonObject;
import com.progwml6.ironshulkerbox.common.block.AbstractIronShulkerBoxBlock;
import com.progwml6.ironshulkerbox.common.registraton.IronShulkerBoxesRecipes;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.ShapedRecipe;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ShulkerBoxBlock;

public class IronShulkerBoxCraftingRecipe extends ShapedRecipe {

  public IronShulkerBoxCraftingRecipe(ResourceLocation id, String group, CraftingBookCategory category, int width, int height, NonNullList<Ingredient> ingredients, ItemStack result, boolean showNotification) {
    super(id, group, category, width, height, ingredients, result, showNotification);
  }

  @Override
  public ItemStack assemble(CraftingContainer container, RegistryAccess registryAccess) {
    ItemStack result = super.assemble(container, registryAccess);

    for (int slot = 0; slot < container.getContainerSize(); slot++) {
      ItemStack input = container.getItem(slot);
      if (!isShulkerBox(input)) {
        continue;
      }

      if (input.hasTag() && input.getTag() != null) {
        result.setTag(input.getTag().copy());
      }

      copyBlockEntityIdentity(result);
      break;
    }

    return result;
  }

  private static boolean isShulkerBox(ItemStack stack) {
    if (stack.isEmpty()) {
      return false;
    }
    Block block = Block.byItem(stack.getItem());
    return block instanceof ShulkerBoxBlock || block instanceof AbstractIronShulkerBoxBlock;
  }

  private static void copyBlockEntityIdentity(ItemStack result) {
    if (!(Block.byItem(result.getItem()) instanceof AbstractIronShulkerBoxBlock shulkerBlock)) {
      return;
    }

    CompoundTag blockEntityTag = BlockItem.getBlockEntityData(result);
    if (blockEntityTag == null) {
      return;
    }

    BlockItem.setBlockEntityData(result, shulkerBlock.blockEntityType(), blockEntityTag);
  }

  @Override
  public RecipeSerializer<?> getSerializer() {
    return IronShulkerBoxesRecipes.SHULKER_BOX_CRAFTING;
  }

  public static class Serializer implements RecipeSerializer<IronShulkerBoxCraftingRecipe> {

    @Override
    public IronShulkerBoxCraftingRecipe fromJson(ResourceLocation id, JsonObject json) {
      return wrap(RecipeSerializer.SHAPED_RECIPE.fromJson(id, json));
    }

    @Override
    public IronShulkerBoxCraftingRecipe fromNetwork(ResourceLocation id, FriendlyByteBuf buffer) {
      return wrap(RecipeSerializer.SHAPED_RECIPE.fromNetwork(id, buffer));
    }

    @Override
    public void toNetwork(FriendlyByteBuf buffer, IronShulkerBoxCraftingRecipe recipe) {
      RecipeSerializer.SHAPED_RECIPE.toNetwork(buffer, recipe);
    }

    private static IronShulkerBoxCraftingRecipe wrap(ShapedRecipe shaped) {
      return new IronShulkerBoxCraftingRecipe(
        shaped.getId(),
        shaped.getGroup(),
        shaped.category(),
        shaped.getWidth(),
        shaped.getHeight(),
        shaped.getIngredients(),
        shaped.getResultItem(null),
        shaped.showNotification()
      );
    }
  }
}
