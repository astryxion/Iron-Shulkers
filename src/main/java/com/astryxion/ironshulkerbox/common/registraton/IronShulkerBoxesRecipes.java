package com.astryxion.ironshulkerbox.common.registraton;

import com.astryxion.ironshulkerbox.IronShulkerBoxes;
import com.astryxion.ironshulkerbox.common.recipes.IronShulkerBoxCraftingRecipe;
import com.astryxion.ironshulkerbox.common.recipes.IronShulkerBoxesColoringRecipe;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class IronShulkerBoxesRecipes {

  public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(Registries.RECIPE_SERIALIZER, IronShulkerBoxes.MODID);

  public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<IronShulkerBoxesColoringRecipe>> SHULKER_BOX_COLORING = RECIPE_SERIALIZERS.register(
      "shulker_box_coloring",
      () -> new CustomRecipe.Serializer<>(IronShulkerBoxesColoringRecipe::new));

  public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<IronShulkerBoxCraftingRecipe>> SHULKER_BOX_CRAFTING = RECIPE_SERIALIZERS.register(
      "shulker_box_crafting",
      () -> new RecipeSerializer<>() {
        @Override
        public com.mojang.serialization.MapCodec<IronShulkerBoxCraftingRecipe> codec() {
          return IronShulkerBoxCraftingRecipe.CODEC;
        }

        @Override
        public StreamCodec<RegistryFriendlyByteBuf, IronShulkerBoxCraftingRecipe> streamCodec() {
          return IronShulkerBoxCraftingRecipe.STREAM_CODEC;
        }
      });
}
