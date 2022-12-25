package andronomos.concretestairs.registry;

import andronomos.concretestairs.ConcreteStairs;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.Arrays;
import java.util.function.Supplier;

public class ModBlocks {
	private static Block.Properties PROPERTIES = Block.Properties.of(Material.STONE)
			.strength(3.0F, 3.0F)
			.sound(SoundType.STONE);

	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, ConcreteStairs.MODID);

	static {
		Arrays.stream(DyeColor.values()).forEach(dyeColor -> {
			registerStair(dyeColor);

			registerBlock(dyeColor.getName() + "_concrete_slab", () -> new SlabBlock(PROPERTIES), ModItems.GetBaseProperties());
			registerBlock(dyeColor.getName() + "_concrete_wall", () -> new WallBlock(PROPERTIES), ModItems.GetBaseProperties());
		});
	}

	private static void registerStair(DyeColor dyeColor) {
		Block inputBlock = ForgeRegistries.BLOCKS.getValue(new ResourceLocation("minecraft", dyeColor.getName() + "_concrete"));
		String name = dyeColor.getName() + "_concrete_stairs";
		registerBlock(name, ()-> new StairBlock(() -> inputBlock.defaultBlockState(), BlockBehaviour.Properties.copy(inputBlock)), ModItems.GetBaseProperties());
	}

	private static <BLOCK extends Block> RegistryObject<BLOCK> registerBlock(final String name, final Supplier<BLOCK> blockFactory, Item.Properties properties) {
		return registerBlock(name, blockFactory, block -> new BlockItem(block, properties));
	}

	private static <BLOCK extends Block> RegistryObject<BLOCK> registerBlock(final String name, final Supplier<BLOCK> blockFactory, final IBlockItemFactory<BLOCK> itemFactory) {
		final RegistryObject<BLOCK> block = BLOCKS.register(name, blockFactory);
		ModItems.ITEMS.register(name, () -> itemFactory.create(block.get()));
		return block;
	}

	/**
	 * A factory function used to create block items.
	 *
	 * @param <BLOCK> The block type
	 */
	@FunctionalInterface
	public interface IBlockItemFactory<BLOCK> {
		Item create(BLOCK block);
	}
}
