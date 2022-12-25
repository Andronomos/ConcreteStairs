package andronomos.concretestairs.data.client;

import andronomos.concretestairs.ConcreteStairs;
import andronomos.concretestairs.registry.ModBlocks;
import net.minecraft.core.Registry;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.data.LanguageProvider;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.apache.commons.lang3.text.WordUtils;

import static andronomos.concretestairs.ConcreteStairs.TAB_NAME;

public class ModLanguageProvider extends LanguageProvider {
	public ModLanguageProvider(DataGenerator gen, String locale) {
		super(gen, ConcreteStairs.MODID, locale);
	}

	@Override
	protected void addTranslations() {
		add("itemGroup." + TAB_NAME, "Concrete Stairs");

		ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get).forEach(b -> {
			String name = ForgeRegistries.BLOCKS.getKey(b).getPath();
			name = name.replaceAll("_", " ");
			name = WordUtils.capitalize(name);
			add(b, name);
		});
	}
}
