package andronomos.concretestairs.data.client;

import andronomos.concretestairs.ConcreteStairs;
import andronomos.concretestairs.registry.ModBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.WallBlock;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockStateProvider extends BlockStateProvider {
	public ModBlockStateProvider(DataGenerator gen, ExistingFileHelper exFileHelper) {
		super(gen, ConcreteStairs.MODID, exFileHelper);
	}

	@Override
	protected void registerStatesAndModels() {
		ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get).forEach(b -> {
			String blockName = ForgeRegistries.BLOCKS.getKey(b).getPath();
			String blockType = b.getClass().getSimpleName();
			String cleanName;

			if ("StairBlock".equals(blockType)) {
				cleanName = blockName.substring(0, blockName.indexOf("_stairs"));
				registerStairBlockStateAndModel((StairBlock) b, blockName, cleanName);
			} else if ("SlabBlock".equals(blockType)) {
				cleanName = blockName.substring(0, blockName.indexOf("_slab"));
				registerSlabBlockStateAndModel((SlabBlock) b, blockName, cleanName);
			} else if ("WallBlock".equals(blockType)) {
				cleanName = blockName.substring(0, blockName.indexOf("_wall"));
				registerWallBlockStateAndModel((WallBlock) b, blockName, cleanName);
			}
		});
	}

	private void registerStairBlockStateAndModel(StairBlock block, String blockName, String cleanName) {
		stairsBlock(block, new ResourceLocation("minecraft", "block/" + cleanName));
		registerItemModel(blockName);
	}

	private void registerSlabBlockStateAndModel(SlabBlock block, String blockName, String cleanName) {
		slabBlock(block, new ResourceLocation("minecraft", "block/" + cleanName), new ResourceLocation("minecraft", "block/" + cleanName));
		registerItemModel(blockName);
	}

	private void registerWallBlockStateAndModel(WallBlock block, String blockName, String cleanName) {
		wallBlock(block, new ResourceLocation("minecraft", "block/" + cleanName));
		itemModels().wallInventory(blockName, new ResourceLocation("minecraft", "block/" + cleanName));
	}

	private void registerItemModel(String name) {
		itemModels().withExistingParent(name, modLoc("block/" + name));
	}
}
