package andronomos.concretestairs.data.client;

import andronomos.concretestairs.ConcreteStairs;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModItemModelProvider extends ItemModelProvider {
	public ModItemModelProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
		super(generator, ConcreteStairs.MODID, existingFileHelper);
	}

	@Override
	protected void registerModels() {

	}

	private ItemModelBuilder createSingleTexture(String name) {
		//return singleTexture(name, mcLoc("item/generated"), "layer0", modLoc("item/" + name));
		return singleTexture(name, mcLoc("item/generated"), "layer0", new ResourceLocation("minecraft", "item/" + name));
	}
}
