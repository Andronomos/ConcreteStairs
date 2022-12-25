package andronomos.concretestairs.data;

import andronomos.concretestairs.registry.ModBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.SingleItemRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider {
	public ModRecipeProvider(DataGenerator generator) {
		super(generator);
	}

	@Override
	protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {
		super.buildCraftingRecipes(consumer);

		ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get).forEach(b -> {
			String blockName = ForgeRegistries.BLOCKS.getKey(b).getPath();
			String blockType = b.getClass().getSimpleName();
			String inputBlockName;

			switch (blockType) {
				case "StairBlock":
					inputBlockName = blockName.substring(0, blockName.indexOf("_stairs"));
					generateStairRecipe((StairBlock) b, inputBlockName, consumer);
					generateStoneCutterRecipe(b, blockName, inputBlockName, 1, consumer);
					break;
				case "SlabBlock":
					inputBlockName = blockName.substring(0, blockName.indexOf("_slab"));
					generateSlabRecipe((SlabBlock) b, inputBlockName, consumer);
					generateStoneCutterRecipe(b, blockName, inputBlockName, 2, consumer);
					break;
				case "WallBlock":
					inputBlockName = blockName.substring(0, blockName.indexOf("_wall"));
					generateWallRecipe(b, inputBlockName, consumer);
					generateStoneCutterRecipe(b, blockName, inputBlockName, 3, consumer);
					break;
			}
		});
	}

	private void generateStairRecipe(StairBlock output, String blockName, Consumer<FinishedRecipe> consumer) {
		Block inputBlock = ForgeRegistries.BLOCKS.getValue(new ResourceLocation("minecraft", blockName));

		ShapedRecipeBuilder shaped = ShapedRecipeBuilder.shaped(output, 4);
		shaped.define('#', inputBlock);
		shaped.pattern("#  ");
		shaped.pattern("## ");
		shaped.pattern("###");
		shaped.unlockedBy("has_item", has(inputBlock));
		shaped.save(consumer);
	}

	private void generateSlabRecipe(SlabBlock output, String blockName, Consumer<FinishedRecipe> consumer) {
		Block inputBlock = ForgeRegistries.BLOCKS.getValue(new ResourceLocation("minecraft", blockName));

		ShapedRecipeBuilder.shaped(output, 6)
				.define('#', inputBlock)
				.pattern("###")
				.unlockedBy("has_item", has(inputBlock))
				.save(consumer);
	}

	private void generateWallRecipe(Block output, String blockName, Consumer<FinishedRecipe> consumer) {
		Block inputBlock = ForgeRegistries.BLOCKS.getValue(new ResourceLocation("minecraft", blockName));

		ShapedRecipeBuilder.shaped(output, 6)
				.define('#', inputBlock)
				.pattern("###")
				.pattern("###")
				.unlockedBy("has_item", has(inputBlock))
				.save(consumer);
	}

	private void generateStoneCutterRecipe(Block output, String outputBlockName, String inputBlockName, int amount, Consumer<FinishedRecipe> consumer) {
		Block input = ForgeRegistries.BLOCKS.getValue(new ResourceLocation("minecraft", inputBlockName));

		SingleItemRecipeBuilder.stonecutting(Ingredient.of(input), output, amount)
				.unlockedBy("has_item", has(input))
				.save(consumer, outputBlockName + "_from_stonecutting");
	}
}
