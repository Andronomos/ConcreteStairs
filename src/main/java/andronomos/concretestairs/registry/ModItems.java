package andronomos.concretestairs.registry;

import andronomos.concretestairs.ConcreteStairs;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModItems {
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ConcreteStairs.MODID);

	public static Item.Properties GetBaseProperties() {
		return new Item.Properties().tab(ConcreteStairs.CONCRETESTAIRS_TAB);
	}
}
