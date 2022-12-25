package andronomos.concretestairs.data;

import andronomos.concretestairs.ConcreteStairs;
import andronomos.concretestairs.registry.ModBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.WallBlock;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockTagsProvider extends BlockTagsProvider {
	public ModBlockTagsProvider(DataGenerator generatorIn, ExistingFileHelper existingFileHelper) {
		super(generatorIn, ConcreteStairs.MODID, existingFileHelper);
	}

	@Override
	protected void addTags() {
		ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get).forEach(b -> {
			tag(BlockTags.MINEABLE_WITH_PICKAXE).add(b);

			if(b instanceof WallBlock wall) {
				tag(BlockTags.WALLS).add(wall);
			}
		});
	}

	@Override
	public String getName() {
		return "ConcreteStairs Tags";
	}
}
